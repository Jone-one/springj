<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>这是index</title>
</head>
<script src="/statics/js/jquery-1.11.1.min.js"></script>

<body>

<div style="width: 100%;height: 100%;">
    <h1>INDEX</h1>
    <div style="margin-left: auto;margin-top: auto;">
        <form>
            <em>KEY:</em>&nbsp;&nbsp;&nbsp;&nbsp;
            <input class="key" type="text" name="key" />
            <em>VALUE:</em>&nbsp;&nbsp;
            <input class="value" type="text" name="value" />
            <input class="submitredis" type="button" value="提交redis" /><br />
        </form>
        <em>reKey:</em>&nbsp;
        <input class="rekey" type="text" disabled="disabled" />
        <em>reValue:</em>&nbsp;
        <input class="revalue" type="text" disabled="disabled" />&nbsp;
        <input class="getredis" type="button" value="读取redis" />
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $(".submitredis").click(function() {
            //					$.post("/springj/home/redis",{})
            var data = $("form").serialize();
            data = DealRoleJson.RoleFormToJson(data);
            $.post(
                "springj/home/redis",
                data,
                function(data,status){
                    alert(data)
                });
        });
    });
    var DealRoleJson = {
        RoleFormToJson: function(data) {
            data = data.replace(/&/g, "\",\"");
            data = data.replace(/=/g, "\":\"");
            data = "{\"" +data+"\"}";
            return data;
        }
    }
</script>
</body>

</html>