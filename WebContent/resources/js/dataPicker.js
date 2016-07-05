

var monthsNames = ["Jnuary", "February", "March", "April", "May", "June", "July",
"August", "September", "October", "November", "December" ];
var telPrefix = ["085", "086", "087", "089"];
var d = new Date();var year = d.getFullYear();
var globalMonthValue=null;var globalYearValue=null;var globalDayValue=null;
function monthOptons(m){
    var sel = document.getElementById("pickMonth");
    for(i = 0, y = 0; i <= m.length; i++){
        var opt = document.createElement("option");
        if(i===0){opt.text="";opt.value=-1;}else{opt.text = m[y];opt.value = y++;}
        sel.appendChild(opt);
    }
}
function dayOptions(m, y){
    var sel = document.getElementById("pickDay");
    var d = 31;
    if(m==null){d=31;}
    else if(y==null)
    {
        if(m==3 || m==5 || m==8 || m==10){d=30;}
        else if(m==1){d=28;}
        else{d==31;}
    }
    else
    {
        if(((y%4)==0)&&(m==1)){d=29;}
        else if(((y%4)!=0)&&(m==1)){d=28;}
        else if(m==3 || m==5 || m==8 || m==10){d=30;}
        else{d=31;}
    }
    while(sel.firstChild){sel.removeChild(sel.firstChild);}
    for(i = 0; i <=d; i++){
        var opt = document.createElement("option");
        if(i===0){opt.text="";opt.value=-1;}else{opt.text = i;opt.value=i;}
        if(i==globalDayValue){opt.selected = true;}
        sel.appendChild(opt);
    }
}
function yearOptions(start, end){
    var sel = document.getElementById("pickYear");
    var range = end - start;
    for(i = 0; i <= range; i++){
        var opt = document.createElement("option");
        if(i===0){opt.text="";opt.value=-1;}else{opt.text = start;opt.value = start++;}
        sel.appendChild(opt);
    }
}
function telPrefixOptions(){
	var sel = document.getElementById("selTelPrefix");
	for (i=0,y=0;i<=telPrefix.length;i++){
		var opt = document.createElement("option");
		if(i===0){opt.text="";opt.value=-1;}else{opt.text=telPrefix[y];opt.value=telPrefix[y++];}
		sel.appendChild(opt);
	}
}

{
    monthOptons(monthsNames);dayOptions(null, null);yearOptions((year-80),(year-16));telPrefixOptions()
    var selectMonthChange = document.getElementById("pickMonth");
    selectMonthChange.addEventListener("change", function(){
        globalMonthValue = this.options[this.selectedIndex].value;
        if(globalMonthValue == -1) {globalMonthValue=null;}
        dayOptions(globalMonthValue, globalYearValue);
    });
    var selectYearChange = document.getElementById("pickYear");
    selectYearChange.addEventListener("change", function(){
        globalYearValue = this.options[this.selectedIndex].value;
        if(globalYearValue == -1) {globalYearValue=null;}
        dayOptions(globalMonthValue, globalYearValue);
    });
    var selectDayChange = document.getElementById("pickDay");
    selectDayChange.addEventListener("change", function(){
        globalDayValue = this.options[this.selectedIndex].value;
        if(globalDayValue == -1) {globalDayValue=null;}
    });
}
