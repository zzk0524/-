define("lesson/index2.0",["lesson/js/common","store","lesson/js/asidebar","common/js/antifake","common/js/checkphone","//static.mukewang.com/static/moco/player/3.0.6/mocoplayer.js","juicer","pagination"],function(e,t,i,n){window.App={};var o={Choice:null,CodeEditor:null,Task:null},a="visibilityState";"undefined"!=typeof document.mozHidden?a="mozVisibilityState":"undefined"!=typeof document.msHidden?a="msVisibilityState":"undefined"!=typeof document.webkitHidden&&(a="webkitVisibilityState"),App={init:function(){var t=this;if(this.properties(),this.Monitor(),this.bindEvents(),s.init(),OP_CONFIG.mid=$.hash("mid"),!OP_CONFIG.mid)return void(window.location.href=OP_CONFIG.curCourse.href);var i={left:"#lessonLeft",right:"#lessonRight",dragger:"#lessonDrag",container:"#lessonwrap",resize:function(){"video"==t.MEDIAMODE&&c.resize()},stop:function(){e.emit("lesson.resize")}};e.idrag.create(i);var n=["#cont-pannel","#choice-pannel"];moco.imagePreview.init(n),$(window).on("hashchange",function(){h.reset(),t.hashchange()}),$(window).resize(function(){e.emit("lesson.resize")}),e.on("lesson.finish",function(e,t){h.send2(!1,!1,!1,!0,t)}),this.loadMedia(OP_CONFIG.mid)},Api:{init:"/lesson/ajaxmediainfo"},MEDIAINFO:{},NEXTMEDIAINFO:{},MEDIAMODE:"",programdata:null,properties:function(){this.$LessonParent=$("#lessonwrap"),this.$mediaName=$("#g-media-name"),this.$className=$("#g-class-name"),this.$RightParent=$("#lessonRight"),this.$pageLoad=$("#pageload"),this.$mend=$("#mend"),this.getFiles=null,this.Msg=["提交正确。","敲的漂亮。","Wow，好厉害。","成功了，让编程来的更猛烈些吧。","不错，慕兄我看好你！","Nice，你这是年薪过百万的节奏啊！","God，你离成神已经不远了！","这你都能通过，无法直视你的双眸。","好，让我们继续一起愉快的玩耍吧。"],this.directMsg=["这是课程的目录，可以随时唤出目录切换章节学习","在这可以查看本课程全部问答，有不懂得问题就来吧","点击笔记可以直接记录你的随笔，边学边做记录","本课所有的源代码等辅助材料都在这里面，点击查看吧"],this.directTitle=["章节","问答","笔记","材料"]},picTime:function(){return c.getPoint()},getCookie:function(e){return document.cookie.length>0&&(c_start=document.cookie.indexOf(e+"="),-1!=c_start)?(c_start=c_start+e.length+1,c_end=document.cookie.indexOf(";",c_start),-1==c_end&&(c_end=document.cookie.length),unescape(document.cookie.substring(c_start,c_end))):""},setCookie:function(e,t,i){var n=new Date;n.setDate(n.getDate()+i),document.cookie=e+"="+escape(t)+(null==i?"":";expires="+n.toGMTString())+";path=/;domain=.imooc.com"},dispatchClose:function(){$.ajax({url:"//www.imooc.com/wechat/qrcode",type:"post",data:{method:-1},dataType:"jsonp",success:function(e){0==e.result&&($(".js-wechat-box").hide(),App.setCookie("showwechat","1",1))}})},showWechat:function(){$(".js-wechat-box").hide(),App.getCookie("showwechat")||$.ajax({url:"//www.imooc.com/wechat/qrcode",type:"GET",dataType:"jsonp",success:function(e){0==e.result&&!e.data.subscribe&&e.data.qrcode&&($(".js-wechat-box").find("img").attr("src",e.data.qrcode),$(".js-wechat-box").show())}})},bindEvents:function(){var e='<div class="cont-video-container" id="cont-video-container"></div>',i=!1,n=this;$("#cont-pannel").on("click",".js-cont-video-btn",function(){var e=$(this).attr("data-url");require(["common/js/player"],function(t){$("#cont-video-container").show(),$(".js-cont-close-video").show(),t.init({id:"cont-video-container",url:e,videotitile:"视频",width:"100%",height:"400",autostart:!1,events:{onReady:function(){this.play()},onPause:function(){$("#cont-pannel").find(".jwcontrolbar").show(),i=!1},onPlay:function(){setTimeout(function(){$("#cont-pannel").find(".jwcontrolbar").hide(),i=!0},300)},onComplete:function(){i=!1}}}),$(".cont-video").on("mouseover",function(){i&&$("#cont-pannel").find(".jwcontrolbar").show()}).on("mouseout",function(){i&&$("#cont-pannel").find(".jwcontrolbar").hide()})})}),$("#cont-pannel").on("click",".js-cont-close-video",function(){$("#cont-video-container").remove(),$(".js-cont-close-video").hide(),$(this).after(e)}),$(document).on("click",".js-wechat-close",function(){App.dispatchClose()}),$(document).on("click",".direct-box .next",function(){var e=($(".direct-box"),parseInt($(this).attr("data-id"))),t=n.directMsg.length;t>e?n.showGuideTip(e):n.hideGuideTip()}),$(document).on("click",".direct-box .close",function(){n.hideGuideTip()}),$(document).on("click",".js-close-lateTip",function(){$(".lateTip-box").hide();var e=(new Date).getTime();t.set("close_lateTip_time",e)}),$(document).on("click",".js-close-latePop",function(){n.$LessonParent.removeClass("renewal")}),$(document).on("click",".js-renew",function(){n.$LessonParent.addClass("renewal")})},hashchange:function(){if("iPad"==navigator.platform)return void window.location.reload(!0);var e=0,i=OP_CONFIG.mid;window.thePlayer&&window.thePlayer.getCurrentTime&&(e=window.thePlayer.getCurrentTime());var n=t.get("_vt")||{},o=n[i];$("#next-mask").hasClass("in")?delete n[i]:o?(o.t=(new Date).getTime(),o.st=e):(o={t:(new Date).getTime(),st:e},n[i]=o);try{t.set("_vt",n)}catch(a){}var s=$.hash("mid");OP_CONFIG.mid=s,s&&this.switchPage(s)},switchPage:function(e){$("#chapterlist").hasClass("active")&&$(".js-chapter").trigger("click"),window.thePlayer&&(window.thePlayer.pause(),n.stop()),this.showPageLoad(),this.loadMedia(e)},loadMedia:function(t,i,n){var o=this;$.ajax({url:o.Api.init,data:{mid:t},type:"get",dataType:"json",success:function(t){return 0!=t.result?""==t.data?void(window.location.href="/course/"+OP_CONFIG.curCourse.id):void(window.location.href=t.data):(1==t.data.type?$(".js-switch-pic").show():$(".js-switch-pic").hide(),2==t.data.type?App.programdata=t.data.programdata:5==t.data.type&&(App.programdata=t.data.offline),App.MEDIAINFO=t.data.media_info,App.NEXTMEDIAINFO=t.data.next_media_info||null,i&&i(t.data.media_info),e.emit("lesson.mediaChange",t.data.media_info),void 0)},error:function(){$.alert("系统错误！")},complete:function(){"function"==typeof n&&n()}})},setMediaName:function(){this.$mediaName.html("步骤"+this.MEDIAINFO.sub_name+" · "+this.MEDIAINFO.chapter_media+" "+this.MEDIAINFO.name)},setClassName:function(){this.$className.html(this.MEDIAINFO.plan_name)},hidePageLoad:function(){this.$pageLoad.addClass("out").fadeOut(300)},showPageLoad:function(){this.$pageLoad.removeClass("out").fadeIn(300)},showGuideModal:function(){var e=this,t=$("#guide-modal-tpl").html(),i=$("body");i.append(juicer(t,{})),$("#guide-modal-wrap .guide-nav").on("click",function(){e.hideGuideModal()}),setTimeout(function(){$("#guide-modal-wrap").addClass("showed"),$("#guide-modal-block").addClass("showed")},30)},hideGuideModal:function(){$("#guide-modal-wrap").removeClass("showed"),$("#guide-modal-block").removeClass("showed"),setTimeout(function(){$("#guide-modal-wrap").remove(),$("#guide-modal-block").remove(),c.playVideo()},600)},showGuideTip:function(e){var t=this,i=$(".direct-box"),n=$("#asidebar .asidebar-item:nth-child("+(e+1)+")").offset().top,o=t.directMsg.length;o>e&&(i.find(".tip").html(t.directMsg[e]),i.find(".position .p1").html(e+1),i.find(".position .p2").html(t.directMsg.length),i.find(".title").html(t.directTitle[e]),i.find(".next").attr("data-id",e+1),i.css("top",n),e==o-1&&i.find(".next").html("完成"),i.show())},hideGuideTip:function(){$(".direct-box").hide(),setTimeout(function(){c.playVideo(),App.popChapterlist(2100)},200)},popChapterlist:function(e){$("#asidebar .js-chapter").trigger("click");var t=0;$("#asidebar .js-chapter").on("click",function(){t=1});setTimeout(function(){t||$("#asidebar .js-chapter").trigger("click")},e)},Monitor:function(){var i=this,n=$("#addexp"),o=null,a=function(){clearTimeout(o),n.removeClass("active")};e.on("lesson.mediaChange",function(n,o){if($("#note-pannel").hasClass("active")&&$("#note-pannel").find(".js-close").click(),$("#qa-pannel").hasClass("active")&&$("#qa-pannel").find(".js-close").click(),a(),i.$LessonParent.attr("class","lessonwrap"),2==OP_CONFIG.later){var s=(new Date).getTime();(!t.get("close_lateTip_time")||s-t.get("close_lateTip_time")>=288e5)&&$(".lateTip-box").show()}1==o.type?(App.MEDIAMODE="video",i.$LessonParent.addClass("video min")):2==o.type?(App.MEDIAMODE="code",i.$LessonParent.addClass("code")):3==o.type?(App.MEDIAMODE="choice",i.$LessonParent.addClass("choice")):4==o.type?(App.MEDIAMODE="task",i.$LessonParent.addClass("task")):5==o.type?(App.MEDIAMODE="freecode",i.$LessonParent.addClass("freecode")):6==o.type&&(App.MEDIAMODE="imageText",i.$LessonParent.addClass("imageText")),e.emit("lesson.pagePrepared")}),e.on("lesson.pagePrepared",function(){document.title="choice"==App.MEDIAMODE?App.MEDIAINFO.chapter_media+" 选择题-慕课网就业班":"task"==App.MEDIAMODE?App.MEDIAINFO.chapter_media+" 作业题-慕课网就业班":"code"==App.MEDIAMODE?App.MEDIAINFO.chapter_media+" 编程练习-慕课网就业班":"freecode"==App.MEDIAMODE?App.MEDIAINFO.chapter_media+" 自由编程-慕课网就业班":"video"==App.MEDIAMODE?App.MEDIAINFO.chapter_media+" "+App.MEDIAINFO.name+"-慕课网就业班":"imageText"==App.MEDIAMODE?App.MEDIAINFO.chapter_media+" 图文节-慕课网就业班":"就业班-慕课网就业班",i.hidePageLoad()}),e.on("lesson.mpRaised",function(e,t){n.find(".t").html("+ "+t.experience.mp.point+" MP"),n.find(".b").html(t.experience.mp.desc),n.addClass("active"),"undefined"!=typeof t.push_ask&&0!=t.push_ask.length&&$("#qa-tip").show(),o=setTimeout(function(){a()},5e3)}),e.on("code.checked",function(e,t){var t=t||{};t.cid=OP_CONFIG.curCourse.id,"success"==t.type&&(t.msg=i.Msg[Math.floor(Math.random()*i.Msg.length)]),OP_CONFIG.is_skip&&(t.is_skip=1),App.NEXTMEDIAINFO&&(t.mid=App.NEXTMEDIAINFO.id),$("#codeBtn").append(juicer($("#code-checked-tpl3").html(),t)),$(".editor-commit").fadeIn(),$("#codeBtn").on("click","#J_Close",function(e){e.preventDefault(),$(".editor-commit").animate({height:0},function(){$(this).html("").css({height:"",display:"none"}).remove()})})})}};var s={init:function(){var t=this;e.on("lesson.mediaChange",function(e,i){h.reset(),h.start(),t.mediaChange(i)}),e.on("lesson.resize",function(){c.resize()})},callback:function(){var t=[];t.push("#cont-scrollbar"),"choice"==App.MEDIAMODE?t.push("#choice-scrollbar"):"task"==App.MEDIAMODE&&t.push("#task-scrollbar"),setTimeout(function(){e.pannelScrollbarInit(t)},500),e.emit("lesson.pagePrepared")},mediaChange:function(){var e=this;if($(".cont-footer-imageText")&&$(".cont-footer-imageText").remove(),window.thePlayer&&window.thePlayer.destroy&&(window.thePlayer.destroy(),window.thePlayer=null),App.setMediaName(),App.setClassName(),2==OP_CONFIG.later&&require(["lesson/js/renewal"],function(){}),"choice"==App.MEDIAMODE){if(o.Choice)return void o.Choice.init(this.callback,!0);require(["lesson/js/choice"],function(t){o.Choice=t.init(e.callback)})}else if("code"==App.MEDIAMODE)r.init(e.callback);else if("freecode"==App.MEDIAMODE)d.init(e.callback);else if("video"==App.MEDIAMODE)c.start(),c.mask.hide(),c.resize();else if("task"==App.MEDIAMODE){if(o.Task)return void o.Task.init(e.callback,!0);require(["lesson/js/task"],function(t){o.Task=t.init(e.callback)})}else"imageText"==App.MEDIAMODE&&l.init(e.callback)}},c={pannel:"#video-pannel",start:function(){if(this.show(),this.play(),this.bindEvents(),App.NEXTMEDIAINFO)this.rendNext(App.NEXTMEDIAINFO);else{var e={over:!0,cid:OP_CONFIG.curCourse.id};this.rendNext(e)}},bindEvents:function(){var e=this;$("#next-mask").on("click",".js-view-again",function(){e.review()})},show:function(){$(this.pannel).show()},hide:function(){$(this.pannel).hide()},rendNext:function(e){this.showEvalutionTip();var t=$("#next-media-tpl").html(),i=$("#next-media-content");i.html(juicer(t,e))},destroy:function(e){this.hide(),this.mask.hide(!0),h.send2(!1,null,e),this.inst&&this.inst.destroy()},review:function(){this.mask.hide(),window.thePlayer.play(),h.start()},pause:function(){window.thePlayer.pause(),h.reset()},getState:function(){return window.thePlayer.getState()},playVideo:function(){window.thePlayer.play(),h.start()},mask:{dom:$("#next-mask"),show:function(){this.dom.addClass("in")},hide:function(){this.dom.removeClass("in")}},resize:function(){},showGuideModal:function(){t.get("video_first")||(t.set("video_first","1"),this.pause(),App.showGuideModal())},showGuideTip:function(e){t.get("video_first")?App.popChapterlist(2100):(t.set("video_first","1"),this.pause(),App.showGuideTip(e))},play:function(){n.stop();var i=this,o=0,a=null,s=App.MEDIAINFO.id;a=t.get("_vt"),a&&a[s]&&(o=a[s].st||0),$.post("/user/loginhistory",{uid:OP_CONFIG.userInfo.uid,browser_key:OP_CONFIG.browser_key,from_course:OP_CONFIG.curCourse.id+"-"+s}),window.thePlayer&&window.thePlayer.destroy&&(window.thePlayer.destroy(),window.thePlayer=null);for(var c=!0,d=[20],r=0;r<d.length;r++)d[r]==OP_CONFIG.plan_id&&(c=!1);var l="http://class.imooc.com/lesson/m3u8h5?mid="+s+"&cid="+OP_CONFIG.curCourse.id,p=window.location.protocol.split(":")[0];"https"===p&&(l="https://class.imooc.com/lesson/m3u8h5?mid="+s+"&cid="+OP_CONFIG.curCourse.id+"&ssl=1"),window.thePlayer=mocoplayer($("#video-container"),{url:l,title:App.MEDIAINFO.name,showAd:c,currentTime:o,uid:OP_CONFIG.userInfo.uid,phone:OP_CONFIG.userInfo.uid,events:{onReady:function(){if("html5"==this.getPlayType()){var t=this,a=2e3;o||!c?a=0:$("#video-container").append('<div id="js-video-copyright" style="width:100%;height:100%;position:absolute;z-index:599;left:0;top:0;						    	background:#000 url(/static/module/common/img/copyright.png) no-repeat center center;background-size:100% auto"></div>'),videoBox=$("#video-container video"),setTimeout(function(){$("#js-video-copyright").remove(),t.play(),n.start(),i.showGuideTip(0)},a)}e.emit("lesson.pagePrepared")},onComplete:function(){App.showWechat(),h.send2(!0,function(e){e.experience,h.reset()}),i.mask.show(),"html5"==this.getPlayType()&&n.stop()},onPlay:function(){i.mask.hide()},onError:function(){}}})},getPoint:function(){var e=0;return window.thePlayer&&window.thePlayer.getCurrentTime&&(e=window.thePlayer.getCurrentTime()),Math.floor(e)},showEvalutionTip:function(){$.ajax({url:"/lesson/checkcomment?mid="+OP_CONFIG.mid,type:"GET",dataType:"json",success:function(e){e.data&&e.data.show_evaluation&&1==e.data.show_evaluation&&e.data.plan_id>0&&$.confirm("提示",{info:"学了这么久，去分享一下对这门课程的看法吧，评价可得20元优惠券哦！",submit:"立即评价",cancel:"暂不评价",modal:!0,callback:function(){window.open("/sc/"+e.data.plan_id+"/evaluation","_blank")}})}})}},d={pannel:"#code-pannel",init:function(t){var i=App.programdata;i.chapter_seqid=App.MEDIAINFO.chapter_seqid,i.seqid=App.MEDIAINFO.seqid,i.name=App.MEDIAINFO.name,i.file_url&&(i.file_url="//climg.mukewang.com/down/"+i.file_url+"."+i.filetype);var n=$("#freeCont-tpl").html(),o=$("#cont-pannel .content"),a=juicer(n,i);o.html(a),t&&t(),$(".js-cont-video-btn").length&&$(".js-cont-video-btn").click(),"0"==App.MEDIAINFO.user_finished&&setTimeout(function(){e.emit("lesson.finish")},3e4)}},r={pannel:"#code-pannel",init:function(e){var t=this,i="#codeTab .files-block";this.change(),this.loadCodeIde(function(){o.CodeEditor?(t._init(e,!0),o.CodeEditor.init($(i))):require(["lesson/js/code"],function(n){o.CodeEditor=n.create($(i)),t._init(e)}),t.codedrag()})},codedrag:function(){var t=0;$("#viewPort-content").find(".dragiframe")[0]&&(t=1);var i={left:"#codeTab",right:"#viewPort-content",dragger:"#codeDrag",container:"#lessonRightinner",str:"code",start:function(){t&&$("#viewPort-content").find(".dragiframe").hide()},stop:function(){t&&$("#viewPort-content").find(".dragiframe").show()}};e.idrag.create(i)},_init:function(e,t){t||this.bindEvents(),App.getFiles=o.CodeEditor.getFiles(),e&&e()},loadCodeIde:function(e){var t=this;$.ajax({type:"GET",url:"/lesson/code?mid="+App.MEDIAINFO.id,dataType:"html",success:function(i){""!=i&&($(t.pannel).html(i),e&&e()),2==OP_CONFIG.later?$(".js-run").addClass("disabled").after('<div class="replac-run"></div>'):($(".js-run").removeClass("disabled"),$(".replac-run").remove())}})},bindEvents:function(){$(this.pannel).on("click",".js-run",function(){1==$(this).attr("data-ssh")?(o.CodeEditor.saveCode(),o.CodeEditor.oldcommit()):o.CodeEditor.commit()}).on("click",".js-reset",function(){o.CodeEditor.resetCode()}).on("click",".js-code-screen",function(){o.CodeEditor.screenCode()}),$("#J_VC_Commit").on("click",function(){var e=$(".js-verify-code-box .verify-code-ipt").val();return 0==e.length?($(".js-verify-code-box .js-verify-code-tip").html("验证码不能为空").show(),!1):e.length>4?($(".js-verify-code-box .js-verify-code-tip").html("验证码错误").show(),!1):($("#J_VC_Commit").addClass("submiting").val("提交中..."),void o.CodeEditor.commit(e))}),$(document).on("mouseover",".replac-run",function(){$(this).siblings(".js-later-tip").show()}),$(document).on("mouseout",".replac-run",function(){$(this).siblings(".js-later-tip").hide()}),$(document).on("mouseover",".js-later-tip",function(){$(this).show()}),$(document).on("mouseout",".js-later-tip",function(){$(this).hide()})},change:function(){var e=App.programdata;e.chapter_seqid=App.MEDIAINFO.chapter_seqid,e.seqid=App.MEDIAINFO.seqid,e.name=App.MEDIAINFO.name,e.videourl=e.video_url;var t=$("#codeCont-tpl").html(),i=$("#cont-pannel .content"),n=juicer(t,e);i.html(n)}},l={pannel:"#code-pannel",init:function(e){$("#lessonDrag").hide(),this.change(),e&&e()},change:function(){var e={};e.chapter_media=App.MEDIAINFO.chapter_media,e.name=App.MEDIAINFO.name,e.content=App.MEDIAINFO.content_md,e.course_id=App.MEDIAINFO.course_id;var t=$("#iamgeText-tpl").html(),i=$("#cont-pannel .content"),n=juicer(t,e);i.html(n),$(".cont-footer-imageText").remove();var o="";if(App.NEXTMEDIAINFO){i.css({"min-height":$("#cont-scrollbar").height()-70});var a="relative",s=""+App.MEDIAINFO.course_id+"#mid="+App.NEXTMEDIAINFO.id;o='<div class="cont-footer-imageText" style="position:'+a+'"><a href="'+s+'"><input type="button" value="下一节" class="r moco-btn moco-btn-purple"></a></div>',$("#cont-scrollbar").append(o)}h.sendImageText(!1,!1,!1,!0,function(){},0)}},p=function(){$(".bottom-box").delegate(".js-change-qa","click",function(){var e=parseInt($(".showqa").attr("data-qanum")),t=$(this).data("sum"),i=Number(t-1),n=Number(e+1);e==i?n=0:n>i&&(n=0),$(".js-"+e).addClass("hideqa").removeClass("showqa"),$(".js-"+n).removeClass("hideqa").addClass("showqa")}),$(".qa-tip").delegate(".js-close","click",function(){$("#qa-tip").hide()})},u=function(e){var t=0;"undefined"!=typeof e.type&&0!=e.type&&(t=1);var i="";i+='<i class="trangle"></i>				<i class="close-btn imv2-close js-close"></i>',i+=0!=t?'<p class="small-title">问题热烈讨论中，来看看吧</p>':'<p class="small-title">帮同学解答一个问题吧</p>',$.each(e.list,function(n){var o="hideqa";o=0==n?"showqa":"hideqa",i+='<div class="qa-detail '+o+" js-"+n+'"  data-qanum="'+n+'">						<a class="qa-title" href="//class.imooc.com/course/qadetail/'+e.list[n].id+'" target="_blank">							<span class="imv2-help"></span>							'+e.list[n].title+'						</a>						<div class="bottom-box clearfix">',i+=0!=t?'<span class="l add-credit">最高可获3积分</span>':'<span class="l add-exp">巩固知识</span>						<span class="l add-credit">获得学分</span>',i+='<a href="//class.imooc.com/course/qadetail/'+e.list[n].id+'" class="go-answer r js-close" target="_blank">我来回答</a>',e.list.length>1&&(i+='<div class="r change-qa js-change-qa" data-sum="'+e.list.length+'">换个问题</div>'),i+="</div></div>"}),$("#qa-tip").html(i),p()},h=function(){return function(i){if(OP_CONFIG.userInfo){var n=i,o=(new Date).getTime(),s=((new Date).getTime(),null),c=(App.MEDIAMODE,function(t,i,s,c,d,l){if("hidden"!=document[a]){var p,h,m={};p=(new Date).getTime(),h=parseInt(p-o)/1e3,m.mid=s||App.MEDIAINFO.id,m.learn_time=30,"undefined"!=typeof l&&(m.learn_time=l),"video"==App.MEDIAMODE&&(m.video_point=n.getPoint()),"undefined"!=typeof c&&1==c&&(m.isfinish=1);var f={url:"/lesson/ajaxmediauser",data:m,type:"POST",dataType:"json",success:function(t){if(0==t.result){if(o=p,t.data&&(e.emit("lesson.mpRaised",t.data),"undefined"!=typeof t.data.push_ask&&0!=t.data.push_ask.length)){var i=t.data.push_ask;u(i)}}else"-101011"==t.result?r.reset():"-105002"==t.result&&$.alert("你的账号在另一地点登录，已被强迫下线。",{info:"如果不是本人操作，建议你修改密码。",callback:function(){window.location.href="//www.imooc.com/course/program"}});d&&d()}},w=w||!0;w===!1&&(f.async=!1),$.ajax(f)}}),d=function(){s=window.setInterval(function(){c(!0,function(){})},3e4)},r={send2:function(e,t,i,n,o){c(e,t,i,n,o)},reset:function(){o=(new Date).getTime(),s&&clearInterval(s),s=null},start:function(){this.reset(),d()},sendImageText:function(e,t,i,n,o){c(e,t,i,n,o,0)}};return window.onbeforeunload=function(){var e=0;if(window.thePlayer&&window.thePlayer.getCurrentTime&&(e=window.thePlayer.getCurrentTime()),App.MEDIAINFO){var i=t.get("_vt")||{},n=i[App.MEDIAINFO.id];$("#next-mask").hasClass("in")?delete i[App.MEDIAINFO.id]:n?(n.t=(new Date).getTime(),n.st=e):(n={t:(new Date).getTime(),st:e},i[App.MEDIAINFO.id]=n),t.set("_vt",i)}c(!1)},r}}(c)}();window.Video=c;var m=function(){App.init(),i.init()};m()});