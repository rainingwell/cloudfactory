<%--
  Created by IntelliJ IDEA.
  User: Greg
  Date: 2021/7/16
  Time: 21:30
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
    <link rel="stylesheet" href="../../lib/layui-v2.6.3/css/layui.css" media="all">
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
                            <label class="layui-form-label">工厂名称</label>
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

<%--        <script type="text/html" id="toolbarDemo">--%>
<%--            <div class="layui-btn-container">--%>
<%--                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>--%>
<%--                &lt;%&ndash;                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>&ndash;%&gt;--%>
<%--            </div>--%>
<%--        </script>--%>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            {{# if(d.status == "正常"){ }}
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">关停</a>
            {{# } }}
            {{# if(d.status == "关停"){ }}
            <a class="layui-btn layui-btn-xs layui-btn-normal data-count-delete" lay-event="delete">开启</a>
            {{# } }}
        </script>

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
            url: '${pageContext.request.contextPath}/factory/list',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                // {type: "checkbox", width: 50},
                // {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'id', width: 200, title: 'id',hide:true,edit:'text'},
                {field: 'factoryname', width: 200, title: '工厂名称'},
                {field: 'status', width: 200, title: '工厂状态'},
                {field: 'userid', width: 200, title: '所属用户'},
                {field: 'introduction', width: 250, title: '工厂介绍'},
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

        table.on('edit(currentTableFilter)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
            console.log(obj.value); //得到修改后的值
            console.log(obj.field); //当前编辑的字段名
            console.log(obj.data); //所在行的所有相关数据
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

        //     //执行搜索重载
        //     table.reload('currentTableId', {
        //         page: {
        //             curr: 1
        //         }
        //         , where: {
        //             searchParams: result
        //         }
        //     }, 'data');
        //
        //     return false;
        // });


        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
                if (obj.event === 'delete') {  // 监听更改工厂状态的操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
        });

        // 监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            var status=obj.data.status;
            var option=null;
            var option1=null;
            if (status=="正常"){
                option="关停";
                option1="2";
            }else if (status=="关停"){
                option="开启";
                option1="1";
            }
            if (obj.event === 'delete') {
                layer.confirm('确定'+option+'该工厂吗？', function (index) {
                    $.ajax({
                        type:"POST",
                        url:'${pageContext.request.contextPath}/factory/changestatus',
                        data:{id: obj.data.id,
                              status: option1},
                        dataType:"text",
                        success : function(data) {
                            //执行搜索重载
                            table.reload('currentTableId', {

                            }, 'data');
                        },
                        error :function (){

                        }
                    });
                    // obj.del();
                    layer.close(index);
                });
            }
        });

    });
</script>

</body>
</html>
