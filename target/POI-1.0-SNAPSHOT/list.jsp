<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>学生列表</title>
    <!-- 禁止浏览器从本地计算机的缓存中访问页面内容 -->
    <meta http-equiv="pragma" content="no-cache">
    <!-- 控制HTTP缓存 -->
    <meta http-equiv="cache-control" content="no-cache">
    <!-- 设置网页的到期时间 -->
    <meta http-equiv="expires" content="0">
    <!-- 网页关键字 -->
    <meta http-equiv="keywords" content="alumni,jquery,easyui,ssm,,mysql">
    <!-- 网页描述 -->
    <meta http-equiv="description" content="A Simple Alumni">
    <!-- easyui-css -->
    <link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css">
    <link id="themeLink" rel="stylesheet" type="text/css" href="static/easyui/themes/default/easyui.css">
    <!-- easyui-js -->
    <script type="text/javascript" src="static/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/locale/easyui-lang-zh_CN.js"></script>


</head>

<body>
<!-- 工具条 -->
<div id="tb">
    <a id="addBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加新项</a>
    <a id="editBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">添加实验及成绩</a>
    <a id="deleteBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <%--导出--%>
    <a  class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true"  onclick="function downAll() {

         linkAll= 'user/export.do';
         window.location.replace(linkAll);
    }
    downAll()">
        导出
    </a>

    <%--导入--%>
    <table>
        <tr>
            <td><input type="file" id="upload" style="width: 200px;height: 40px"  name="upload" value="" /></td>
            <td><button value="1" name="导入" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="uploadFile()">
                上传数据
            </button></td>
        </tr>
    </table>


</div>

<!-- 学生信息列表 -->
<table id="list"></table>

<!-- 编辑学生信息窗口 -->
<div id="win" class="easyui-window" title="学生信息编辑窗口" style="width:600px;height:435px;padding: 20px 65px;"
     data-options="iconCls:'icon-save',modal:true,closed:true">
    <form id="editForm" method="post" action="">
        <%-- 设置id隐藏域: 用于区分添加与修改逻辑 --%>
        <input type="hidden" name="id">
        <!--
        利用style="border-collapse:separate; border-spacing:0px 13px;调节tr间的距离
        利用cellpadding="6"调节td间的距离
        -->
        <table style="border-collapse:separate; border-spacing:0px 3px;" cellpadding="6">
            <tr>
                <td>姓名</td>
                <td><input class="easyui-textbox" name="name" style="width: 200px;height: 25px" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>学号</td>
                <td><input class="easyui-textbox" name="number" style="width: 200px;height: 25px" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>实验名称</td>
                <td>
                    <input class="easyui-textbox" name="course" style="width: 200px;height: 25px" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>预习</td>
                <td>
                    <input class="easyui-textbox" name="pre" style="width: 100px;height: 25px" data-options="required:true"/>
                </td>
                <td>占比</td>
                <td>
                    <input class="easyui-textbox" name="prep" style="width: 80px;height: 25px" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>操作</td>
                <td>
                    <input class="easyui-textbox" name="operate" style="width: 100px;height: 25px" data-options="required:true"/>
                </td>
                <td>占比</td>
                <td>
                    <input class="easyui-textbox" name="operatep" style="width: 80px;height: 25px" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>结论</td>
                <td>
                    <input class="easyui-textbox" name="conclusion" style="width: 100px;height: 25px" data-options="required:true"/>
                </td>
                <td>占比</td>
                <td>
                    <input class="easyui-textbox" name="conclusionp" style="width: 80px;height: 25px" data-options="required:true"/>
                </td>
            </tr>


        </table>
        <div align="center" style="padding-top: 20px">
            <a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
        </div>
    </form>
</div>






<!-- 加载并展示学生列表信息 -->
<script type="text/javascript">


    $(function () {
        $("#list").datagrid({
            //url:后台数据查询的地址
            url: "user/list.do",
            //columns:填充的列数据
            //field:后台对象的属性
            //tille:列标题
            columns: [[
                {
                    field: "id",
                    title: "编号",
                    width: 100,
                    checkbox: true //单选框
                },
                {
                    field: "name",
                    title: "姓名",
                    width: 100
                },
                {
                    field: "number",
                    title: "学号",
                    width: 500
                },
                {
                    field: "course",
                    title: "实验名称",
                    width: 200
                },
                {
                    field: "pre",
                    title: "预习",
                    width: 100
                },
                {
                    field: "operate",
                    title: "操作",
                    width: 100
                },
                {
                    field: "conclusion",
                    title: "结论",
                    width: 100
                },
                {
                    field: "result",
                    title: "核算结果",
                    width: 100
                },

            ]],
            //显示分页
            pagination: true,
            //工具条
            toolbar: "#tb",
            //显示行号
            rownumbers: true
        });


        //添加按钮事件
        $("#addBtn").click(function () {
            $("#editForm").form("clear");//清空表单数据
            $("#win").window("open");//打开编辑窗口
        });


        //提交按钮事件
        $("#saveBtn").click(function () {
            $("#editForm").form("submit", {
                //url: 提交到后台的地址
                url: "user/save.do",
                //onSubmit: 表单提交前的回调函数,true:提交表单,false:不提交表单
                onSubmit: function () {
                    //判断表单的验证是否都通过了,validate:用作表单字段验证
                    return $("#editForm").form("validate");
                },
                //success: 服务器执行完毕后的回调函数
                //要求Controller返回的数据格式如: 成功:{success:true} 失败:{success:false,msg:错误信息}
                success: function (data) { //data: 服务器返回的类型为字符串的数据
                    var data = eval('(' + data + ')');//把字符串类型的data转换对象类型
                    if (data.success) {
                        $("#win").window("close");//关闭窗口
                        $("#list").datagrid("reload");//刷新datagrid
                        $.messager.alert("提示", "保存成功", "info");
                    } else {
                        $.messager.alert("提示", "保存失败: " + data.msg, "error");
                    }
                }
            });

        });


        //修改按钮事件
        $("#editBtn").click(function () {
            //设置仅单选
            var rows = $("#list").datagrid("getSelections");
            if (rows.length != 1) {
                $.messager.alert("提示", "请选择一行数据进行修改哟 !", "warning");
                return;
            }
            $("#editForm").form("load", "user/editUser.do?id=" + rows[0].id);//表单回显
            $("#win").window("open");//打开编辑窗口
        });


        //删除按钮事件
        $("#deleteBtn").click(function () {
            var rows = $("#list").datagrid("getSelections");
            if (rows.length == 0) {
                $.messager.alert("提示", "请至少选择一行删除哟 !", "warning");
                return;
            }
            //确认删除提示
            $.messager.confirm("提示", "确认要删除所选择的数据吗 ?", function (value) {
                if (value) {

                    //设置多选删除,其格式为: id=1&id=2&id=3
                    var idStr = "";
                    $(rows).each(function (i) {
                        idStr += ("id=" + rows[i].id + "&");
                    });
                    idStr = idStr.substring(0, idStr.length - 1);

                    //将拼装后的id传递到后台
                    $.post("user/delete.do", idStr, function (data) {
                            if (data.success) {
                                $("#list").datagrid("reload");//刷新datagrid
                                $.messager.alert("提示", "删除成功啦 !", "info");
                            } else {
                            $.messager.alert("提示", "删除失败 !：" + data.msg, "error");
                        }
                    }, "json");
                }
            });
        });


    })


    //导入按钮
    function uploadFile() {
        var file = $("#upload").val();
        file = file.substring(file.lastIndexOf('.'), file.length);
        if (file == '') {
            alert("上传文件不能为空！");
        } else if (file != '.xlsx' && file != '.xls') {
            alert("请选择正确的excel类型文件！");
        } else {
            ajaxFileUpload();
        }
    }
    function ajaxFileUpload() {

        var formData = new FormData();
        var name = $("#upload").val();
        formData.append("file", $("#upload")[0].files[0]);
        formData.append("name", name);
        $.ajax({
            url : "user/import.do",
            type : "post",
            async : false,
            data : formData,
            processData : false,
            contentType : false,
            beforeSend : function() {
                console.log("正在进行，请稍候");
            },
            success : function(e) {

                $("#list").datagrid("reload");//刷新datagrid


            }
        });
      /*  $.post("user/import.do", formData, function (data) {
            if (data.success) {
                $("#list").datagrid("reload");//刷新datagrid
                $.messager.alert("提示", "导入成功 !", "info");
            } else {
                $.messager.alert("提示", "导入失败 !：" + data.msg, "error");
            }
        }, "json");
*/


    }


</script>


</body>
</html>
