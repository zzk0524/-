window.onload = function(){
	var list = document.getElementsByClassName("list"),
		smallTitle = document.getElementById("smallTitle");
	var userExit = document.getElementById("userExit"),
		profileUser =document.getElementById("profileUser");
	/*左侧选项鼠标点击不同选项会有样式变化*/
	for(var i = 0;i<list.length;i++){
        list[i].onclick = function(){
        	smallTitle.innerHTML = this.firstChild.innerHTML;
            addClass(this,"current");
            var sib = siblings(this);//获取剩余的兄弟元素
            for(var j = 0;j<sib.length;j++){
                removeClass(sib[j],"current");
            } 
        }
    }
    function addClass(obj,name){       //添加样式函数
        obj.className = obj.className + " " + name;
    }
    function siblings(obj){    //获取到除当前按钮以外其他按钮
        var sibArr = obj.parentNode.children;
        var sibNewArr = [];
        for(var i = 0;i<sibArr.length;i++){
            if(sibArr[i] != obj){
                sibNewArr.push(sibArr[i]);
            }
        }
        return sibNewArr;     
    }
    function removeClass(obj,name){   //删除样式函数
        var classStr = obj.className;
        var classArr = classStr.split(" ");
        var classNewArr = [];
        for(var i = 0;i<classArr.length;i++){//把current类去掉
            if(classArr[i] != name){
                classNewArr.push(classArr[i]);
            }
        }
        obj.className = classNewArr.join(" ");
    }
    /*用户退出区域，鼠标经过隐藏区域出现*/
    profileUser.onmousemove = function(){
    	userExit.style.display = "block";
    	profileUser.style.backgroundColor = "#0099FF";
    	userExit.onmouseover = function(){
    		this.style.display = "block";
    		profileUser.style.backgroundColor = "#0099FF";
    		this.onmouseleave = function(){
    			this.style.display = "none";
    			profileUser.style.backgroundColor = "rgb(3,184,204)";
    		}
    	}
    	this.onmouseleave = function(){
    		userExit.style.display = "none";
    		profileUser.style.backgroundColor = "rgb(3,184,204)";
    	}
    }
}