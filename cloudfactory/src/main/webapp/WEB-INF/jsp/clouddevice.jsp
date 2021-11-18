<%--
  Created by IntelliJ IDEA.
  User: Greg
  Date: 2021/7/16
  Time: 19:12
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
                            <label class="layui-form-label">设备名称</label>
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

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add" style="margin-left: 10px"> 添加 </button>
                <%--                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>--%>

                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="rent" style="margin-left: 70px"> 租用设备 </button>
                <%--                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>--%>
            </div>
        </script>


        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            {{# if(d.devicestatus == "关闭"){ }}
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="option">远程开机</a>
            {{# } }}
            {{# if(d.devicestatus == "闲置中"){ }}
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="option">远程关机</a>
            {{# } }}
            {{# if(d.devicestatus == "生产中"){ }}
            <a class="layui-btn layui-btn-xs layui-bg-orange data-count-edit" >生产中</a>
            {{# } }}
            {{# if(d.devicestatus == "故障"){ }}
            <a class="layui-btn layui-btn-xs layui-bg-orange data-count-edit" >故障</a>
            {{# } }}
            {{# if(d.rentstatus == "自有设备"){ }}
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
            {{# } }}
            {{# if(d.rentstatus == "租用"){ }}
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="rent">停止租用</a>
            {{# } }}
            <a class="layui-btn layui-btn-xs layui-bg-cyan data-count-delete" lay-event="product">配置产品</a>
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
            url: '${pageContext.request.contextPath}/device/list2',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                // {type: "checkbox", width: 50},
                // {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'id', width: 150, title: 'id',hide:true},
                {field: 'devicename', width: 150, title: '设备名称'},
                // {field: 'sex', width: 80, title: '性别', sort: true},
                {field: 'deviceno', width: 180, title: '设备编码'},
                {field: 'norms', width: 100, title: '设备规格'},
                {field: 'typeid', width: 100, title: '设备类别'},
                // {field: 'sign', title: '签名', minWidth: 150},
                {field: 'devicestatus', width: 100, title: '设备状态'},
                {field: 'rentstatus', width: 100, title: '租用状态'},
                {field: 'describe', width: 130, title: '描述'},
                {field: 'factoryid', width: 100, title: '工厂'},
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
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加产品',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '../statics/page/device/add.html',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
        });
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'rent') {  // 监听添加操作
                var index = layer.open({
                    title: '租用设备',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '../statics/page/device/rent.html',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }else if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加产品',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '../statics/page/device/add.html',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }
        });

        // 监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {

                var index = layer.open({
                    title: '编辑设备信息',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '../statics/page/device/edit.html',
                    success: function(layero, index){
                        var body = layer.getChildFrame('body', index);
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                        console.log(body.html()); //得到iframe页的body内容
                        // body.find('input').val(obj.data.id);
                        // body.find ('#/userid').val(obj.data.id);
                        // body.find ('#username').val(obj.data.id);
                        body.find ('#id').val(obj.data.id);
                        body.find ('#deviceno').val(obj.data.deviceno);
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                if(obj.data.rentstatus!=="租用"){
                    layer.confirm('真的删除改设备吗', function (index) {
                        $.ajax({
                            type:"GET",
                            url:'${pageContext.request.contextPath}/device/deleteDevice',
                            data:{id: obj.data.id},
                            dataType:"text",
                            success : function(data) {
                                layer.msg('删除成功');
                            },
                            error : function (){
                                layer.msg('删除失败');
                            }
                        });
                        obj.del();
                        layer.close(index);
                    });
                }else{
                    layer.msg('设备已被租用不允许删除');
                }

                // layer.confirm('确定删除此用户？', {icon: 3, title: '提示信息'}, function (index) {
                //     $.post('/user/deleteUser', {id: obj.data.id}, function (data) {
                //         layer.msg(data.msg);
                //         tableIns.reload();
                //         obj.del();
                //         layer.close(index);
                //     });
                // });
            }else if (obj.event === 'option') {
                var option=null;
                var option1=null;
                if(obj.data.devicestatus==="关闭"){
                    option="远程开机";
                    option1="2";
                }else if (obj.data.devicestatus==="闲置中"){
                    option="远程关机";
                    option1="0";
                }
                layer.confirm('确定'+option+'吗', function (index) {
                    $.ajax({
                        type:"GET",
                        url:'${pageContext.request.contextPath}/device/updateDeviceStatus',
                        data:{id: obj.data.id,
                            devicestatus:option1},
                        dataType:"text",
                        success : function(data) {
                            layer.msg('操作成功');
                            table.reload('currentTableId', 'data');
                        },
                        error : function (){
                            layer.msg('操作失败');
                        }
                    });
                    layer.close(index);
                });
            }else if (obj.event === 'rent') {
                layer.confirm('确定停止租用吗', function (index) {
                    $.ajax({
                        type:"GET",
                        url:'${pageContext.request.contextPath}/device/rent',
                        data:{id: obj.data.id,rent:2},
                        dataType:"text",
                        success : function(data) {
                            layer.msg('操作成功');
                            table.reload('currentTableId', 'data');
                        },
                        error : function (){
                            layer.msg('操作失败');
                        }
                    });
                    layer.close(index);
                });
            }else if (obj.event === 'product') {
                var index = layer.open({
                    title: '配置产品',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '../statics/page/device/product.html',
                    success: function(layero, index){
                        var body = layer.getChildFrame('body', index);
                        var iframe = window['layui-layer-iframe' + index];
                        console.log(body.html()); //得到iframe页的body内容
                        // body.find('input').val(obj.data.id);
                        // body.find ('#/userid').val(obj.data.id);
                        // body.find ('#username').val(obj.data.id);
                        body.find ('#id').val(obj.data.id);
                        body.find ('#deviceno').val(obj.data.deviceno);
                        body.find ('#devicename').val(obj.data.devicename);
                        iframe.child(obj.data.id);
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            }
        });

    });
</script>

</body>
</html>
