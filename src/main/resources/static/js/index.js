changeNav2('image',2);
getUser();


function getUser() {
    $.ajax({
		type:'get',
		url:'adminController/getLoginUser',
		success:function (result) {
			if(result.roleid==1){
				$('#nn').append('<li id="nn3"><a href="javascript:changeNav2(-1,3)">管理员管理</a></li>');
			}
        },
		error:function (error) {
            bootbox.alert("访问服务器失败");
        }
	})
}

function changeNav2(url, id) {
	for(var i = 1; i <= 3; i++) {
		if(i == id) {
			$('#nn' + i).addClass('active');
		} else {
			$('#nn' + i).removeClass('active');
		}
	}
	if(url==-1)
		url='user'
	$('#iframe').attr('src', url);
}

function reinitIframe() {
	var iframe = document.getElementById("iframe");
	try {
		var navHight = document.getElementById("nav").offsetHeight;
		var bodyHight = document.documentElement.clientHeight;
		var bottomHight = document.getElementById("bnav").offsetHeight;
		iframe.style.marginTop = navHight+'px';
		iframe.style.marginBottom = bottomHight+'px';
		// var bHeight = iframe.contentWindow.document.body.scrollHeight;
		// var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
		// var height = Math.max(bHeight, dHeight);
		iframe.height = bodyHight-navHight-bottomHight;
	} catch(ex) {}
}
window.setInterval("reinitIframe()", 200);

function closeDesignPanel() {
    $('#desginPanel').hide();
    $('#nikeName').val("");
    $('#phone').val("");
    prizePic = null;
}

function openDesignPanel() {
	$('#desigenSumbit').attr('onclick', 'add()');
    $('#desginPanel').show();
}

function check() {
    if ($('#oldP').val() == null || $('#oldP').val() == "") {
        bootbox.alert("原密码为空");
        return false;
    } else if ($('#newP1').val() == null || $('#newP1').val() == "") {
        bootbox.alert("新密码不能为空")
        return false;
    } else if ($('#newP1').val() != $('#newP2').val()) {
        bootbox.alert("两次密码不一致")
        return false;
    }else {
        return true;
    }
}



function add() {
    if (check()) {
        $.ajax({
            type: 'post',
            url: './adminController/updateP',
            data: {
                oldP: $('#oldP').val(),
                newP: $('#newP1').val(),
            },
            success: function (result) {
                if (result.result == 1) {
                    alert("修改成功");
                    closeDesignPanel();
                } else {
                    alert("修改失败，请检查原密码是否正确");
                }
            },
            error: function (error) {
                alert("访问服务器失败")
            }
        })
    }
}