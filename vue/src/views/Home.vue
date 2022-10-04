<template>
    <div>
        <el-row :gutter="10" style="margin-bottom: 150px">
            <el-col :span="6">
                <el-card style="color: #409EFF">
                    <div><i class="el-icon-user-solid"></i>用户总数</div>
                    <div style="padding: 10px 0;text-align: center;font-weight: bold">
                        100
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card style="color: #edd014">
                    <div><i class="el-icon-money"></i>销售总量</div>
                    <div style="padding: 10px 0;text-align: center;font-weight: bold">
                        $1000000
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card style="color: #00c317">
                    <div><i class="el-icon-bank-card"></i>收益总额</div>
                    <div style="padding: 10px 0;text-align: center;font-weight: bold">
                        $1000000
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card style="color: #dd2215">
                    <div><i class="el-icon-s-shop"></i>门店总数</div>
                    <div style="padding: 10px 0;text-align: center;font-weight: bold">
                        20
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-col :span="12">
            <div id="main" style="width: 500px;height: 500px"></div>
        </el-col>

        <el-col :span="12">
            <div id="pie" style="width: 500px;height: 500px"></div>
        </el-col>
    </div>
</template>

<script>
    // 引入Echarts
    import * as echarts from 'echarts'

    export default {
        name: "Home",
        data() {
            return {}
        },
        // 页面元素渲染之后再触发
        mounted() {
            // 柱状折线图
            var option = {
                title: {
                    text: '各季度会员数量统计',
                    subtext: '趋势图',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                xAxis: {
                    type: 'category',
                    data: ["第一季度", "第二季度", "第三季度", "第四季度"]
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: "星巴克",
                        data: [],
                        type: 'bar'
                    },
                    {
                        name: "星巴克",
                        data: [],
                        type: 'line'
                    },
                    {
                        name: "瑞幸咖啡",
                        data: [],
                        type: 'bar'
                    },
                    {
                        name: "瑞幸咖啡",
                        data: [],
                        type: 'line'
                    }
                ]
            };
            var chartDom = document.getElementById('main');
            var myChart = echarts.init(chartDom);
            this.request.get("/echarts/members").then(res => {
                // 填空
                option.series[0].data = res.data;
                option.series[1].data = res.data;
                option.series[2].data = [5, 6, 7, 8];
                option.series[3].data = [5, 6, 7, 8];
                // 在数据准备完毕之后再set
                myChart.setOption(option);
            })

            // 饼图
            var pieOption = {
                title: {
                    text: '各季度会员数量统计',
                    subtext: '比例图',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [
                    {
                        name: '星巴克',
                        type: 'pie',
                        radius: '35%',
                        center: ['25%', '50%'],
                        label: {            //饼图图形上的文本标签
                            normal: {
                                show: true,
                                position: 'inner', //标签的位置
                                textStyle: {
                                    fontWeight: 300,
                                    fontSize: 14,    //文字的字体大小
                                    color: "#fff"
                                },
                                formatter: '{d}%'
                            }
                        },
                        data: [],  // 填空
                    },
                    {
                        name: '瑞幸咖啡',
                        type: 'pie',
                        radius: '45%',
                        center: ['75%', '60%'],
                        label: {            //饼图图形上的文本标签
                            normal: {
                                show: true,
                                position: 'inner', //标签的位置
                                textStyle: {
                                    fontWeight: 300,
                                    fontSize: 14,    //文字的字体大小
                                    color: "#fff"
                                },
                                formatter: '{c}({d}%)'
                            }
                        },
                        data: [{name: "第一季度", value: 5},
                            {name: "第二季度", value: 6},
                            {name: "第三季度", value: 7},
                            {name: "第四季度", value: 8},],  // 填空
                    }
                ]
            };
            var pieDom = document.getElementById('pie');
            var pieChart = echarts.init(pieDom);
            this.request.get("/echarts/members").then(res => {
                pieOption.series[0].data = [
                    {name: "第一季度", value: res.data[0]},
                    {name: "第二季度", value: res.data[1]},
                    {name: "第三季度", value: res.data[2]},
                    {name: "第四季度", value: res.data[3]},
                ]
                pieChart.setOption(pieOption)
            })
        }
    }
</script>

<style scoped>

</style>