<%--
  Created by IntelliJ IDEA.
  User: Greg
  Date: 2021/7/13
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../statics/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../../statics/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">订单编码</label>
                            <div class="layui-input-inline">
                                <input type="text" name="name" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>


        <script type="text/html" id="currentTableBar">
            {{# if(d.orderstatus == "投标结束"){ }}
            <a class="layui-btn layui-btn-xs" lay-event="paichan">完成排产</a>
            {{# } }}
        </script>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>


    </div>
</div>
<script src="../../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="../../statics/js/jquery-1.8.3.min.js"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#currentTableId',
            method: 'post',
            url: '${pageContext.request.contextPath}/order/list4',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                // {type: "checkbox", width: 50},
                // {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'orderno', width: 180, title: '订单编码'},
                {field: 'username', width: 100, title: '经销商'},
                {field: 'id', width: 150, title: 'id',hide:true},
                {field: 'userid', width: 150, title: 'userid',hide:true},
                // {field: 'sex', width: 80, title: '性别', sort: true},
                {field: 'ordernum', width: 100, title: '订单数量'},
                {field: 'orderstatus', width: 100, title: '订单状态'},
                {field: 'productid', width: 150, title: '产品'},
                // {field: 'sign', title: '签名', minWidth: 150},
                {field: 'receipt', width: 100, title: '收货人'},
                {field: 'deliverDate', width: 180, title: '交付日期',templet:
                        function(value){
                            return layui.util.toDateString(value.deliverDate);
                        }},
                {field: 'deaddate', width: 180, title: '投标截止日期',templet:
                        function(value){
                            return layui.util.toDateString(value.deaddate);
                        }},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line',
            request: {
                pageName: 'page',   // 页码的参数名称，默认：page
                limitName: 'size'   // 每页数据量的参数名，默认：limit
            },
        });


        // 监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });
        table.on('tool(currentTableFilter)', function (obj) {
            var option="4";
             if (obj.event === 'paichan') {
                layer.confirm('确定排产完成吗', function (index) {
                    $.ajax({
                        type:"GET",
                        url:'${pageContext.request.contextPath}/order/updatestatus',
                        data:{id: obj.data.id,option:option},
                        dataType:"text",
                        success : function(data) {
                            layer.msg('完成排产');
                        },
                        error : function (){
                            layer.msg('error');
                        }
                    });
                    layer.close(index);
                });
            }
        });





        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            var result = JSON.stringify(data.field);
            layer.alert(result, {
                title: '最终的搜索信息'
            });

            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                }
                , where: {
                    // searchParams: result
                    name: data.field.name
                }
            }, 'data');
            return false;
        });


    });
</script>

</body>
</html>