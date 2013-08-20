// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
//

function  WWHBookGroups_Books(ParamTop)
{


  ParamTop.fAddDirectory("installationguide", null, null, null, null);
  ParamTop.fAddDirectory("reference", null, null, null, null);
}

function  WWHBookGroups_ShowBooks()
{
  return true;
}

function  WWHBookGroups_ExpandAllAtTop()
{
  return false;
}
