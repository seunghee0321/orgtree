'use strict';
const gBuildNo = "20220811";
const gMsgCfmTitle = "CLOUD OFFICE ORG CONFIRM."; 
const gMsgInfoTitle = "CLOUD OFFICE ORG INFO."; 
const gDelay1 = 1000;
const gDelay2 = 1500;
const gDelay3 = 3000;
const gSuccess = "SUCCESS";

function gfnResizeStop(newwidth, index) {
    $('.ui-jqgrid-bdiv').css('overflow', $(this).width() < 886 ? 'hidden' : 'auto');
}

// 현재 사용 안함. 
function gfnUpdatePagerIcons(table) {
    var iconClass = {
        'ui-icon-seek-first': 'fa fa-angle-double-left bigger-140',
        'ui-icon-seek-prev': 'fa fa-angle-left bigger-140',
        'ui-icon-seek-next': 'fa fa-angle-right bigger-140',
        'ui-icon-seek-end': 'fa fa-angle-double-right bigger-140'
    };
    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function() {
        var $class = $.trim($(this).attr('class').replace('ui-icon', ''));
        if($class in iconClass) {
            $(this).attr('class', 'ui-icon ' + iconClass[$class]);
        }
    });
}

function gfnDate2YYYYMMDD(date) {
	if (typeof date == "undefined" || date == "Invalid date" || date == null || date == "") {
		return "";
	}
	else {
		let year = date.getFullYear();
		let month = (1 + date.getMonth());
		month = month >= 10 ? month : '0' + month;
		let day = date.getDate();
		day = day >= 10 ? day : '0' + day;
		
		return year.toString() + month.toString() + day.toString();
	}
}

function gfnYYYYMMDD2Date(str) {
	let rt = "";

	if (typeof str == "undefined" || str == "Invalid date" || str == null || str == "") {
		rt = "";
	}
	else {
		try {
			let y = str.substr(0, 4);
			let m = str.substr(4, 2) - 1;
			let d = str.substr(6, 2);
			rt = new Date(y,m,d);
		} 
		catch (e) {
			rt = "";
		}
	}
	
    return rt;
}

function gfnYmdFormat(ymd) {
	let rtValue = "";
	
	try {
		if (ymd == null || ymd.length < 8) {
			rtValue = "";
		}
		else {
			let year = ymd.substr(0, 4);
			let mm = ymd.substr(4, 2);
			let dd = ymd.substr(6, 2);
			rtValue = year.toString() + "-" + mm.toString() + "-" + dd.toString();
		}
	} 
	catch (e) {
		rtValue = "";
	}
	
	return rtValue;
}

function gfnMdFormat(md) {
	let rtValue = "";
	
	try {
		if (md == null || md.length < 4) {
			rtValue = "";
		}
		else {
			let mm = md.substr(0, 2);
			let dd = md.substr(2, 2);
			rtValue = mm + "/" + dd;
		}
	} 
	catch (e) {
		rtValue = "";
	}
	
	return rtValue;
}

function gfnSelectMenu(menuGroupName, menuItemName) {
	$("#" + menuGroupName).addClass("active");
	
	if (menuItemName != "" && menuItemName != null && typeof menuItemName != "undefined") {
		$("#" + menuItemName).addClass("active");
	}
}

function gfnNull2Zero(str) {
	if (typeof str == "undefined" || str == "" || str == null) {
		str = "0";
	}
	return str;
}

function gfnNull2Space(str) {
	if (typeof str == "undefined" || str == "Invalid date" || str == null) {
		str = "";
	}
	return str;
}

function gfnNull2Default(str, defaultStr) {
	if (typeof str == "undefined" || str == "Invalid date" || str == null || str == "") {
		str = defaultStr;
	}
	return str;
}

function gfnAddComma(num) {
	return num.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
}

function gfnDateFormat(cellValue, options, rowObject) {
	return gfnYmdFormat(cellValue);
}

function gfnDateFormat2(cellValue, options, rowObject) {
	let rtValue = "";
	
	try {
		if (cellValue == null || cellValue.length < 10) {
			rtValue = "";
		}
		else {
			rtValue = cellValue.substr(0, 10);
		}
	} 
	catch (e) {
		rtValue = "";
	}
	
	return rtValue;
}
