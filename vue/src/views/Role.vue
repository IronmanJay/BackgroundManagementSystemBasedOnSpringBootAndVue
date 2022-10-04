<template>
    <!--     页面主体       -->
    <div>
        <!--        搜索部分        -->
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search"
                      v-model="name"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <!--      表格外部操作部分          -->
        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-popconfirm
                    class="ml-5"
                    confirm-button-text='确定'
                    cancel-button-text='我再想想'
                    icon="el-icon-info"
                    icon-color="red"
                    title="您确定批量删除这些数据吗？"
                    @confirm="delBatch"
            >
                <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
        </div>

        <!--        表格内部操作部分        -->
        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"
                  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="name" label="名称"></el-table-column>
            <el-table-column prop="flag" label="唯一标识"></el-table-column>
            <el-table-column prop="description" label="描述"></el-table-column>
            <el-table-column label="操作" width="280" align="center">
                <template slot-scope="scope">
                    <el-button type="info" @click="selectMenu(scope.row)">分配菜单 <i class="el-icon-menu"></i>
                    </el-button>
                    <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-popconfirm
                            class="ml-5"
                            confirm-button-text='确定'
                            cancel-button-text='我再想想'
                            icon="el-icon-info"
                            icon-color="red"
                            title="您确定删除吗？"
                            @confirm="del(scope.row.id)">
                        <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

        <!--       翻页与页码部分         -->
        <div style="padding: 10px 0">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNum"
                    :page-sizes="[2, 5, 10, 20]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>

        <el-dialog title="角色信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form label-width="80px" size="small">
                <el-form-item label="名称">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="唯一标识">
                    <el-input v-model="form.flag" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input v-model="form.description" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="菜单分配" :visible.sync="menuDialogVis" width="30%">
            <el-tree
                    :props="props"
                    :data="menuData"
                    show-checkbox
                    node-key="id"
                    ref="tree"
                    :default-expanded-keys="expends"
                    :default-checked-keys="checks">
                <span class="custom-tree-node" slot-scope="{ node, data }">
                    <span><i :class="data.icon"></i> {{ data.name }}</span>
                </span>
            </el-tree>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveRoleMenu">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<!--页面数据与动作Js代码-->
<script>
    export default {
        name: "Role",
        data() {
            return {
                tableData: [],
                total: 0,
                pageNum: 1,
                pageSize: 10,
                name: "",
                form: {},
                dialogFormVisible: false,
                menuDialogVis: false,
                multipleSelection: [],
                menuData: [],
                props: {
                    label: 'name',
                },
                expends: [],
                checks: [],
                roleId: 0,
                roleFlag: '',
            }
        },
        // 请求分页查询数据
        created() {
            this.load()
        },
        methods: {
            // 将数据库查询操作封装
            load() {
                this.request.get("/role/page", {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        name: this.name,
                    }
                }).then(res => {
                    console.log(res)
                    this.tableData = res.records
                    this.total = res.total

                });
            },
            save() {
                this.request.post("/role", this.form).then(res => {
                    if (res) {
                        this.$message.success("保存成功")
                        this.dialogFormVisible = false
                        this.load()
                    } else {
                        this.$message.error("保存失败")
                    }
                })
            },
            saveRoleMenu() {
                this.request.post("/role/roleMenu/" + this.roleId, this.$refs.tree.getCheckedKeys()).then(res => {
                    if (res.code === '200') {
                        this.$message.success("绑定成功")
                        this.menuDialogVis = false
                        // 操作管理员角色后需要重新登陆
                        if (this.roleFlag === 'ROLE_ADMIN') {
                            this.$store.commit("logout")
                        }
                    } else {
                        this.$message.error(res.msg)
                    }
                })
            },
            handleAdd() {
                this.dialogFormVisible = true
                this.form = {}
            },
            handleEdit(row) {
                this.form = row
                this.dialogFormVisible = true
            },
            del(id) {
                this.request.delete("/role/" + id).then(res => {
                    if (res) {
                        this.$message.success("删除成功")
                        this.load()
                    } else {
                        this.$message.error("删除失败")
                    }
                })
            },
            handleSelectionChange(val) {
                console.log(val)
                this.multipleSelection = val
            },
            delBatch() {
                let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
                this.request.post("/role/del/batch", ids).then(res => {
                    if (res) {
                        this.$message.success("批量删除成功")
                        this.load()
                    } else {
                        this.$message.error("批量删除失败")
                    }
                })
            },
            reset() {
                this.name = ""
                this.load()
            },
            // 动态分页请求
            handleSizeChange(pageSize) {
                console.log(pageSize)
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange(pageNum) {
                console.log(pageNum)
                this.pageNum = pageNum
                this.load()
            },
            selectMenu(role) {
                this.menuDialogVis = true;
                this.roleId = role.id;
                this.roleFlag = role.flag;
                // 请求菜单数据
                this.request.get("/menu", {}).then(res => {
                    console.log(res);
                    this.menuData = res.data;
                    // 把后台返回的菜单数据处理成id数组
                    this.expends = this.menuData.map(v => v.id)
                })
                this.request.get("/role/roleMenu/" + this.roleId).then(res => {
                    // 先渲染弹窗里的元素
                    this.menuDialogVis = true
                    this.checks = res.data;
                    this.request.get("/menu/ids").then(r => {
                        const ids = r.data
                        ids.forEach(id => {
                            if (!this.checks.includes(id)) {
                                this.$refs.tree.setChecked(id, false)
                            }
                        })
                    })
                })
            },
        }
    }
</script>

<!--表格头部样式-->
<style>
    .headerBg {
        background: #eee !important;
    }
</style>