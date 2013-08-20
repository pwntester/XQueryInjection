// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
//

function  WWHJavaScriptSettings_Object()
{
  this.mHoverText = new WWHJavaScriptSettings_HoverText_Object();

  this.mTabs   = new WWHJavaScriptSettings_Tabs_Object();
  this.mTOC    = new WWHJavaScriptSettings_TOC_Object();
  this.mIndex  = new WWHJavaScriptSettings_Index_Object();
  this.mSearch = new WWHJavaScriptSettings_Search_Object();
}

function  WWHJavaScriptSettings_HoverText_Object()
{
  this.mbEnabled = true;

  this.mFontStyle = "font-family: Verdana, Arial, Helvetica, sans-serif ; font-size: 10pt";

  this.mWidth = 150;

  this.mForegroundColor = "#000000";
  this.mBackgroundColor = "#FFFFCC";
  this.mBorderColor     = "#999999";
}

function  WWHJavaScriptSettings_Tabs_Object()
{
  this.mFontStyle = "font-family: Verdana, Arial, Helvetica, sans-serif ; font-size: 10pt";

  this.mSelectedTabColor       = "#3366CC";
  this.mSelectedTabBorderColor = "#FFFFFF";
  this.mSelectedTabTextColor   = "#FFFFFF";

  this.mDefaultTabColor       = "#CCCCCC";
  this.mDefaultTabBorderColor = "#666666";
  this.mDefaultTabTextColor   = "#000000";
}

function  WWHJavaScriptSettings_TOC_Object()
{
  this.mbShow = true;

  this.mFontStyle = "font-family: Verdana, Arial, Helvetica, sans-serif ; font-size: 10pt";

  this.mEnabledColor  = "#003399";
  this.mDisabledColor = "black";

  this.mIndent = 17;
}

function  WWHJavaScriptSettings_Index_Object()
{
  this.mbShow = true;

  this.mFontStyle = "font-family: Verdana, Arial, Helvetica, sans-serif ; font-size: 10pt";

  this.mEnabledColor  = "#003399";
  this.mDisabledColor = "black";

  this.mIndent = 17;

  this.mNavigationFontStyle     = "font-family: Verdana, Arial, Helvetica, sans-serif ; font-size: 7pt ; font-weight: bold";
  this.mNavigationCurrentColor  = "black";
  this.mNavigationEnabledColor  = "#003399";
  this.mNavigationDisabledColor = "#999999";
}

function  WWHJavaScriptSettings_Index_DisplayOptions(ParamIndexOptions)
{
  ParamIndexOptions.fSetThreshold(1);

  ParamIndexOptions.fSetSeperator(" - ");

  ParamIndexOptions.fGroup("Numerics", false, true, "1234567890");
  ParamIndexOptions.fGroup("", true, false, "");
  ParamIndexOptions.fGroup("A", false, true, "A");
  ParamIndexOptions.fGroup("B", false, true, "B");
  ParamIndexOptions.fGroup("C", false, true, "C");
  ParamIndexOptions.fGroup("D", false, true, "D");
  ParamIndexOptions.fGroup("E", false, true, "E");
  ParamIndexOptions.fGroup("F", false, true, "F");
  ParamIndexOptions.fGroup("G", false, true, "G");
  ParamIndexOptions.fGroup("H", false, true, "H");
  ParamIndexOptions.fGroup("I", false, true, "I");
  ParamIndexOptions.fGroup("J", false, true, "J");
  ParamIndexOptions.fGroup("K", false, true, "K");
  ParamIndexOptions.fGroup("L", false, true, "L");
  ParamIndexOptions.fGroup("M", false, true, "M");
  ParamIndexOptions.fGroup("N", false, true, "N");
  ParamIndexOptions.fGroup("O", false, true, "O");
  ParamIndexOptions.fGroup("P", false, true, "P");
  ParamIndexOptions.fGroup("Q", false, true, "Q");
  ParamIndexOptions.fGroup("R", false, true, "R");
  ParamIndexOptions.fGroup("S", false, true, "S");
  ParamIndexOptions.fGroup("T", false, true, "T");
  ParamIndexOptions.fGroup("U", false, true, "U");
  ParamIndexOptions.fGroup("V", false, true, "V");
  ParamIndexOptions.fGroup("W", false, true, "W");
  ParamIndexOptions.fGroup("X", false, true, "X");
  ParamIndexOptions.fGroup("Y", false, true, "Y");
  ParamIndexOptions.fGroup("Z", false, true, "Z");
  ParamIndexOptions.fGroup("Symbols", false, true, "!@#$%^&*(){}[]<>\"|\\.,;-?+");
}

function  WWHJavaScriptSettings_Search_Object()
{
  this.mbShow = true;

  this.mFontStyle = "font-family: Verdana, Arial, Helvetica, sans-serif ; font-size: 10pt";

  this.mEnabledColor  = "#003399";
  this.mDisabledColor = "black";

  this.mIndent = 17;

  this.mbShowBook = true;
  this.mbShowRank = true;
}
