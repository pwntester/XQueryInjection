/*
Copyright Scand LLC http://www.scbr.com
To use this component please contact info@scbr.com to obtain license

*/ 
 

 
dhtmlXTreeObject.prototype.enableRTL=function(mode){
 var z=convertStringToBoolean(mode);
 if(((z)&&(!this.rtlMode))||((!z)&&(this.rtlMode)))
{
 this.rtlMode=z;
 this._switchToRTL(this.rtlMode);
}
};



dhtmlXTreeObject.prototype._switchToRTL=function(mode){
 if(mode){
 this.allTree.className=
 this._ltr_line=this.lineArray;
 this._ltr_min=this.minusArray;
 this._ltr_plus=this.plusArray;
 this.lineArray=new Array("line2_rtl.gif","line3_rtl.gif","line4_rtl.gif","blank.gif","blank.gif","line1_rtl.gif");
 this.minusArray=new Array("minus2_rtl.gif","minus3_rtl.gif","minus4_rtl.gif","minus.gif","minus5_rtl.gif");
 this.plusArray=new Array("plus2_rtl.gif","plus3_rtl.gif","plus4_rtl.gif","plus.gif","plus5_rtl.gif");
 this.allTree.className="containerTableStyleRTL";
}
 else
{
 this.allTree.className="containerTableStyle";
 this.lineArray=this._ltr_line;
 this.minusArray=this._ltr_min;
 this.plusArray=this._ltr_plus;
}
 if(this.htmlNode.childsCount)
 this._redrawFrom(this,this.htmlNode);
};




