// expresiones regulares para reemplazar tildes y eñes.
var ntildeRe = new RegExp ('&ntilde;', 'g') ;
var ntildeCapRe = new RegExp ('&Ntilde;', 'g');
var aacuteRe = new RegExp ('&aacute;', 'g');
var aacuteCapRe = new RegExp ('&Aacute;', 'g');
var eacuteRe = new RegExp ('&eacute;', 'g');
var eacuteCapRe = new RegExp ('&Eacute;', 'g');
var iacuteRe = new RegExp ('&iacute;', 'g');
var iacuteCapRe = new RegExp ('&Iacute;', 'g');
var oacuteRe = new RegExp ('&oacute;', 'g');
var oacuteCapRe = new RegExp ('&Oacute;', 'g');
var uacuteRe = new RegExp ('&uacute;', 'g');
var uacuteCapRe = new RegExp ('&Uacute;', 'g');
var uumlRe = new RegExp ('&uuml;', 'g');
var uumlCapRe = new RegExp ('&Uuml;', 'g');

function replaceCharacterEntities(str) {
	
	str = str.replace(ntildeRe, 'ñ');
	str = str.replace(ntildeCapRe, 'Ñ');
	str = str.replace(aacuteRe, 'á');
	str = str.replace(aacuteCapRe, 'Á');
	str = str.replace(eacuteRe, 'é');
	str = str.replace(eacuteCapRe, 'É');
	str = str.replace(iacuteRe, 'í');
	str = str.replace(iacuteCapRe, 'Í');
	str = str.replace(oacuteRe, 'ó');
	str = str.replace(oacuteCapRe, 'Ó');
	str = str.replace(uacuteRe, 'ú');
	str = str.replace(uacuteCapRe, 'Ú');
	str = str.replace(uumlRe, 'ü');
	str = str.replace(uumlCapRe, 'Ü');
	return str;
}

function numeric(e, decimal, negative) {
		     var k, retVal;
		     document.all ? k = e.keyCode : k = e.which;
		     if( decimal == 1 ){
		     	retVal = ((k > 47 && k < 58) || (k > 95 && k < 106) || k == 8 || k == 9|| k == 188 || k == 190 || k == 46 || k == 110 || 
		     	(k > 36 && k < 40) ||
		     	k == 98 || k == 100 || 
		     	k == 102 || k == 104);
		     }else{
		     	retVal = ((k > 47 && k < 58) || (k > 95 && k < 106) || k == 8 || k == 9 || k == 39 ||
		     	(k > 36 && k < 40) ||
		     	k == 98 || k == 100 || 
		     	k == 102 || k == 104);		     	
		     }
		     if( negative == 1 ){
		    	retVal = retVal || ( k == 189 ) || ( k == 109 );
		     }
     		return retVal;
		}

function validarEmail(valor) {
	
	var exp=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
	if (exp.test(valor)){
		return true;
	}
	else
	{
		return false;
	}
}

function isEmpty(strValue){
	var strTemp = strValue;
	strTemp = trimAll(strTemp);
	
	if(strTemp.length > 0){
		return true;
	}
	
	return false;
}

function validSubmit() {
	var result = false;
	submitCount++;
	
	if (submitCount == 1) {
		result = true;
	} else {
		alert(processingRequestMessage);
	}	
	return result;
}

function singleSubmit(form) {

	var result = validSubmit();
	if (result) {
		form.submit();
	} 	
	return result;
}

function isNumeric(strValue){
	var objRegExp = /(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/; 
	return objRegExp.test(strValue);
}

function isInteger(strValue){
	  var objRegExp  = /(^-?\d\d*$)/;	  
	  return objRegExp.test(strValue);
}

function noPaste(event, obj){

	if (event.modifiers && Event.CONTROL_MASK){
	  return false; //netscape 4.0
	}
    else if (event.ctrlKey && (event.keyCode == 86)){
    	return false;//iexplorer, netscape 6.0, firefox
    }
    else return true;
    
}

function extractNumber(obj, decimalPlaces, allowNegative)
{
	var temp = obj.value;
	
	// avoid changing things if already formatted correctly
	var reg0Str = '[0-9]*';
	if (decimalPlaces > 0) {
		reg0Str += '\\.?[0-9]{0,' + decimalPlaces + '}';
	} else if (decimalPlaces < 0) {
		reg0Str += '\\.?[0-9]*';
	}
	reg0Str = allowNegative ? '^-?' + reg0Str : '^' + reg0Str;
	reg0Str = reg0Str + '$';
	var reg0 = new RegExp(reg0Str);
	if (reg0.test(temp)) return true;

	// first replace all non numbers
	var reg1Str = '[^0-9' + (decimalPlaces != 0 ? '.' : '') + (allowNegative ? '-' : '') + ']';
	var reg1 = new RegExp(reg1Str, 'g');
	temp = temp.replace(reg1, '');

	if (allowNegative) {
		// replace extra negative
		var hasNegative = temp.length > 0 && temp.charAt(0) == '-';
		var reg2 = /-/g;
		temp = temp.replace(reg2, '');
		if (hasNegative) temp = '-' + temp;
	}
	
	if (decimalPlaces != 0) {
		var reg3 = /\./g;
		var reg3Array = reg3.exec(temp);
		if (reg3Array != null) {
			// keep only first occurrence of .
			//  and the number of places specified by decimalPlaces or the entire string if decimalPlaces < 0
			var reg3Right = temp.substring(reg3Array.index + reg3Array[0].length);
			reg3Right = reg3Right.replace(reg3, '');
			reg3Right = decimalPlaces > 0 ? reg3Right.substring(0, decimalPlaces) : reg3Right;
			temp = temp.substring(0,reg3Array.index) + '.' + reg3Right;
		}
	}
	
	obj.value = temp;
}

function validateValue(strValue, strMatchPattern ) {
	var objRegExp = new RegExp( strMatchPattern);
	alert(objRegExp.test(strValue));
	return objRegExp.test(strValue);
}

function validateInputString(event, object, pattern){
	var strValue = object.value;
	var valid = true;
	
	valid = validateValue(strValue, pattern);
	
	if (!valid){
		strValue = strValue.substring(0, strValue.length - 1);
	}
	
	return valid;
}

function ltrim(s) {
	  	return s.replace(/^\s+/, "");
}

function rtrim(s) {
		return s.replace(/\s+$/, "");
}
/* Metodo que elimina espacios en blanco */
function trim(s) {	
		return rtrim(ltrim(s));
}
//regresa true si dateStr es una fecha válida con formato dd/mm/yyyy
function isValidDate(dateStr) {
	var formatDate = 'dd/mm/yyyy';
	var datePat = /^(\d{2})(\/|-)(\d{2})\2(\d{4})$/;
	var matchArray = dateStr.match(datePat);
	if (matchArray == null) {
		return false;
	}
	day = matchArray[1]; 
	month = matchArray[3];
	year = matchArray[4];
	if (month < 1 || month > 12) { 
		return false;
	}
	if (day < 1 || day > 31) {
		return false;
	}
	if ((month==4 || month==6 || month==9 || month==11) && day==31) {
		return false;
	}
	if (month == 2) { 
	var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
	if (day>29 || (day==29 && !isleap)) {
		return false;
	   }
	}
	return true;
}