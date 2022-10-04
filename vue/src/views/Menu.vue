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
            <el-button type="primary" @click="handleAdd(null)">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
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
        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" row-key="id" default-expand-all
                  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="name" label="名称"></el-table-column>
            <el-table-column prop="path" label="路径"></el-table-column>
            <el-table-column prop="pagePath" label="页面路径"></el-table-column>
            <el-table-column label="图标" class-name="fontSize18" align="center" label-class-name="fontSize12">
                <template slot-scope="scope">
                    <span :class="scope.row.icon"/>
                </template>
            </el-table-column>
            <el-table-column prop="description" label="描述"></el-table-column>
            <el-table-column label="操作" width="300" align="center">
                <template slot-scope="scope">
                    <el-button type="primary" @click="handleAdd(scope.row.id)" v-if="!scope.row.pid && !scope.row.path">
                        新增子菜单 <i class="el-icon-plus"></i>
                    </el-button>
                    <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-popconfirm
                            class="ml-5"
                            confirm-button-text='确定'
                            cancel-button-text='我再想想'
                            icon="el-icon-info"
                            icon-color="red"
                            title="您确定删除吗？"
                            @confirm="del(scope.row.id)"
                    >
                        <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="菜单信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form label-width="80px" size="small">
                <el-form-item label="名称">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="路径">
                    <el-input v-model="form.path" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="页面路径">
                    <el-input v-model="form.pagePath" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="图标">
                    <el-select clearable v-model="form.icon" placeholder="请选择" style="width: 100%">
                        <el-option v-for="item in options" :key="item.name" :label="item.name" :value="item.value">
                            <i :class="item.value"/> {{ item.name }}
                        </el-option>
                    </el-select>
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
                multipleSelection: [],
                options: [],
            }
        },
        // 请求分页查询数据
        created() {
            this.load()
        },
        methods: {
            // 将数据库查询操作封装
            load() {
                this.request.get("/menu", {
                    params: {
                        name: this.name,
                    }
                }).then(res => {
                    this.tableData = res.data;
                })
            },
            save() {
                this.request.post("/menu", this.form).then(res => {
                    if (res) {
                        this.$message.success("保存成功")
                        this.dialogFormVisible = false
                        this.load()
                    } else {
                        this.$message.error("保存失败")
                    }
                })
            },
            handleAdd(pid) {
                this.dialogFormVisible = true;
                this.form = {};
                if (pid) {
                    this.form.pid = pid
                }
            },
            handleEdit(row) {
                this.form = row
                this.dialogFormVisible = true
                // 请求图标的数据
                this.request.get("/menu/icons").then(res => {
                    this.options = res.data
                })
            },
            del(id) {
                this.request.delete("/menu/" + id).then(res => {
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
                this.request.post("/menu/del/batch", ids).then(res => {
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
            exp() {
                window.open("http://localhost:9090/menu/export")
            },
            handleExcelImportSuccess() {
                this.$message.success("文件导入成功!")
                this.load()
            }
        }
    }
</script>

<style>
    .headerBg {
        background: #eee !important;
    }

    .fontSize18 {
        font-size: 18px;
    }

    .fontSize12 {
        font-size: 12px;
    }
</style>