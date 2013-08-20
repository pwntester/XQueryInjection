// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
//

function  WWHCommonSettings_Object()
{
  this.mbForceJavaScript = true;

  this.mbSyncContentsEnabled  = true;
  this.mbPrevEnabled          = true;
  this.mbNextEnabled          = true;
  this.mbRelatedTopicsEnabled = false;
  this.mbEmailEnabled         = false;
  this.mbPrintEnabled         = true;
  this.mbBookmarkEnabled      = true;

  this.mEmailAddress = "Your Company";

  this.mRelatedTopics = new WWHCommonSettings_RelatedTopics_Object();
  this.mPopup         = new WWHCommonSettings_Popup_Object();

  this.mbHighlightingEnabled        = true;
  this.mHighlightingForegroundColor = "#FFFFFF";
  this.mHighlightingBackgroundColor = "#333399";
}

function  WWHCommonSettings_RelatedTopics_Object()
{
  this.mTitleFontStyle       = "font-family: Verdana, Arial, Helvetica, sans-serif ; font-size: 10pt";
  this.mTitleForegroundColor = "#FFFFFF";
  this.mTitleBackgroundColor = "#999999";

  this.mFontStyle = "font-family: Verdana, Arial, Helvetica, sans-serif ; font-size: 10pt";

  this.mWidth = 250;

  this.mForegroundColor = "#003399";
  this.mBackgroundColor = "#FFFFFF";
  this.mBorderColor     = "#666666";

  this.mbInlineEnabled = true;

  this.mInlineFontStyle = "font-family: Verdana, Arial, Helvetica, sans-serif ; font-size: 10pt";

  this.mInlineForegroundColor = "#003366";
}

function  WWHCommonSettings_Popup_Object()
{
  this.mWidth = 200;

  this.mBackgroundColor = "#FFFFCC";
  this.mBorderColor     = "#999999";
}
