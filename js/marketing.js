window.onload = function(){
	var mergeDay = document.getElementById("mergeDay"),
		gross = document.getElementById("gross"),
		sale = document.getElementById("sale"),
		grossAndSale = document.getElementById("grossAndSale");
	var dayBtn = document.getElementById("dayBtn"),//近七天
		monthBtn = document.getElementById("monthBtn"),//近六月
		inSevenDays = document.getElementById("inSevenDays"),
		inSixMonth = document.getElementById("inSixMonth");
	var mergeMonth = document.getElementById("mergeMonth"),
		gross1 = document.getElementById("gross1"),
		sale1 = document.getElementById("sale1"),
		grossAndSale1 = document.getElementById("grossAndSale1");
	var barChart = document.getElementById("barChart"),
		pieChart = document.getElementById("pieChart"),
		inAndOut = document.getElementById("inAndOut"),
		goodsEarnings = document.getElementById("goodsEarnings");
	var todayBtn = document.getElementById("todayBtn"),
		thisMonthBtn = document.getElementById("thisMonthBtn"),
		todays = document.getElementById("todays"),
		thisMonth = document.getElementById("thisMonth");
	mergeDay.onclick = function(){
		if(this.innerHTML=="分开查看"){
			grossAndSale.style.display = "none";
			gross.style.display = "block";
			sale.style.display = "block";
			this.innerHTML = "合并两图";
		}else{
			gross.style.display = "none";
			sale.style.display = "none";
			grossAndSale.style.display = "block";
			this.innerHTML = "分开查看";
		}
	}
    dayBtn.onclick = function(){
    	this.classList.remove("active");
    	monthBtn.classList.remove("active");
    	this.classList.add("active");
    	inSixMonth.style.display = "none";
    	inSevenDays.style.display = "block";
    }  
    monthBtn.onclick = function(){
    	this.classList.remove("active");
    	dayBtn.classList.remove("active");
    	this.classList.add("active");
    	inSevenDays.style.display = "none";
    	inSixMonth.style.display = "block";
    }  
    mergeMonth.onclick = function(){
		if(this.innerHTML=="分开查看"){
			grossAndSale1.style.display = "none";
			gross1.style.display = "block";
			sale1.style.display = "block";
			this.innerHTML = "合并两图";
		}else{
			gross1.style.display = "none";
			sale1.style.display = "none";
			grossAndSale1.style.display = "block";
			this.innerHTML = "分开查看";
		}
	}
	barChart.onclick = function(){
    	this.classList.remove("curr");
    	pieChart.classList.remove("curr");
    	this.classList.add("curr");
    	goodsEarnings.style.display = "none";
    	inAndOut.style.display = "block";
    }  
    pieChart.onclick = function(){
    	this.classList.remove("curr");
    	barChart.classList.remove("curr");
    	this.classList.add("curr");
    	inAndOut.style.display = "none";
    	goodsEarnings.style.display = "block";
    }  
    todayBtn.onclick = function(){
    	this.classList.remove("active");
    	thisMonthBtn.classList.remove("active");
    	this.classList.add("active");
    	thisMonth.style.display = "none";
    	todays.style.display = "block";
    }  
    thisMonthBtn.onclick = function(){
    	this.classList.remove("active");
    	todayBtn.classList.remove("active");
    	this.classList.add("active");
    	todays.style.display = "none";
    	thisMonth.style.display = "block";
    }  
}