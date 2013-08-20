var planExplainTree;
var planExplainMenu;
var usage = new Array();
var usage_count = 0;
var usage_index = 0;
var adaptors = new Array();
var adaptors_count = 0;
var adaptors_index = 0;
var functions = new Array();
var functions_count = 0;
var functions_index = 0;


function fontSizeMenu(addSeparator, addCopy)
{
	addCopy = true;
	var separator='';
	if(addSeparator == true) {
		separator="<MenuItem name='&lt;hr/&gt;' id='separator'/>";
	}
	separator+="<MenuItem name='Font Size ...' id='fontSize' src='font.gif'><MenuItem name='8px' id='8px' src='font.gif'/><MenuItem name='10px' id='10px' src='font.gif'/><MenuItem name='12px' id='12px' src='font.gif'/><MenuItem name='16px' id='16px' src='font.gif'/><MenuItem name='18px' id='18px' src='font.gif'/><MenuItem name='22px' id='22px' src='font.gif'/><MenuItem name='24px' id='24px' src='font.gif'/></MenuItem>";
	if(addCopy && window.clipboardData) {
		separator+="<MenuItem name='Copy' src='' id='copy' width='120px' panelWidth='120'/>";
	}
	return separator;
}

function copyToClipboard(text)
{
	if(window.clipboardData) {
		window.clipboardData.setData('text',text);
	}
}

function GoToAdaptor(name) {
   for(var i=0;i<planExplainTree._globalIdStorageSize;i++) {
      var image = planExplainTree.getItemImage(planExplainTree._globalIdStorage[i]);
      if(image=='adaptor.gif') {
          var text = planExplainTree.getItemText(planExplainTree._globalIdStorage[i]);
          if(text==name) {
              planExplainTree.selectItem(planExplainTree._globalIdStorage[i]);
              planExplainTree.focusItem(planExplainTree._globalIdStorage[i]);
              return;
          } 
      }
   }
   alert('Adaptor definition was not found');
}


function GoToFunction(name) {
   for(var i=0;i<planExplainTree._globalIdStorageSize;i++) {
      var image = planExplainTree.getItemImage(planExplainTree._globalIdStorage[i]);
      if(image=='function.gif') {
          var text = planExplainTree.getItemText(planExplainTree._globalIdStorage[i]);
          if(text==name) {
              planExplainTree.selectItem(planExplainTree._globalIdStorage[i]);
              planExplainTree.focusItem(planExplainTree._globalIdStorage[i]);
              return;
          } 
      }
   }
   alert('Function definition was not found');
}



function HasDefinition(name) {
   var split=name.split(' ');
   if(split[3]!=null) name = split[3];
   else name = split[0];
   for(var i=0;i<planExplainTree._globalIdStorageSize;i++) {
      var image = planExplainTree.getItemImage(planExplainTree._globalIdStorage[i]);
      if(image=='var.gif' || image=='constructor.gif') {
          var text = planExplainTree.getItemText(planExplainTree._globalIdStorage[i]);
          var split=text.split(' ');
          text = split[0];
          if(text==name) {
              return true;
          } 
      }
   }
   return false;
}


function GoToDefinition(name) {
   var split=name.split(' ');
   if(split[3]!=null) name = split[3];
   else name = split[0];
   for(var i=0;i<planExplainTree._globalIdStorageSize;i++) {
      var image = planExplainTree.getItemImage(planExplainTree._globalIdStorage[i]);
      if(image=='var.gif' || image=='constructor.gif') {
          var text = planExplainTree.getItemText(planExplainTree._globalIdStorage[i]);
          var split=text.split(' ');
          text = split[0];
          if(text==name) {
              planExplainTree.selectItem(planExplainTree._globalIdStorage[i]);
              planExplainTree.focusItem(planExplainTree._globalIdStorage[i]);
              usage = new Array();
              usage_count = 0;
              usage_index = 0;
              return;
          } 
      }
   }
   alert('Variable definition was not found');
}

function FindReferences(name) {
   var split=name.split(' ');
   name = split[0];
   usage = new Array();
   usage_count = 0;
   usage_index = 0;
   for(var i=0;i<planExplainTree._globalIdStorageSize;i++) {
      var image = planExplainTree.getItemImage(planExplainTree._globalIdStorage[i]);
      if(image=='varRef.gif') {
         var text  = planExplainTree.getItemText(planExplainTree._globalIdStorage[i]);         
         var split=text.split(' ');
         text = split[0];
         if(split[3]!=null) text=split[3];
         if(text==name) {             
             usage[usage_count] = planExplainTree._globalIdStorage[i];
             usage_count++;
         }
      }
   }
}

function FindAdaptorUsage(name) {
   adaptors = new Array();
   adaptors_count = 0;
   adaptors_index = 0;
   for(var i=0;i<planExplainTree._globalIdStorageSize;i++) {
      var image = planExplainTree.getItemImage(planExplainTree._globalIdStorage[i]);
      if(image=='dbSource.gif') {
         var text  = planExplainTree.getItemText(planExplainTree._globalIdStorage[i]);
         var l = text.split('(')[1];
         var url = l.split(')')[0];
         if(url==name) {
             adaptors[adaptors_count] = planExplainTree._globalIdStorage[i];
             adaptors_count++;
         }
      }
   }
}


function FindFunctionCalls(name) {
   functions = new Array();
   functions_count = 0;
   functions_index = 0;
   for(var i=0;i<planExplainTree._globalIdStorageSize;i++) {
      var image = planExplainTree.getItemImage(planExplainTree._globalIdStorage[i]);
      if(image=='call.gif') {
         var text  = planExplainTree.getItemText(planExplainTree._globalIdStorage[i]);
         if(text==name) {
             functions[functions_count] = planExplainTree._globalIdStorage[i];
             functions_count++;
         }
      }
   }
}


function NextReference() {
   if(usage_index<usage_count-1) {
       usage_index++;
       planExplainTree.selectItem(usage[usage_index]);
       planExplainTree.focusItem(usage[usage_index]);
   }
}

function PrevReference() {
   if(usage_index>0) {
       usage_index--;
       planExplainTree.selectItem(usage[usage_index]);
       planExplainTree.focusItem(usage[usage_index]);
   }
}


function NextAdaptorReference() {
   if(adaptors_index<adaptors_count-1) {
       adaptors_index++;
       planExplainTree.selectItem(adaptors[adaptors_index]);
       planExplainTree.focusItem(adaptors[adaptors_index]);
   }
}

function PrevAdaptorReference() {
   if(adaptors_index>0) {
       adaptors_index--;
       planExplainTree.selectItem(adaptors[adaptors_index]);
       planExplainTree.focusItem(adaptors[adaptors_index]);
   }
}


function NextFunctionCall() {
   if(functions_index<functions_count-1) {
       functions_index++;
       planExplainTree.selectItem(functions[functions_index]);
       planExplainTree.focusItem(functions[functions_index]);
   }
}

function PrevFunctionCall() {
   if(functions_index>0) {
       functions_index--;
       planExplainTree.selectItem(functions[functions_index]);
       planExplainTree.focusItem(functions[functions_index]);
   }
}


function onContextMenuSelect(menuitemId,treeitemId)
{
    if(menuitemId=='copy') {
        copyToClipboard(planExplainTree.getItemText(treeitemId).replace("<B>","").replace("</B>","") );
    }

	if(menuitemId=='openDefinition') {
        GoToDefinition(planExplainTree.getItemText(treeitemId));
    }
    if(menuitemId=='openReferences') {
        if(usage_count>0) {
           planExplainTree.selectItem(usage[0]);
           planExplainTree.focusItem(usage[0]);
        } else {
           alert('References to this variable were not found');
        }
    }
    if(menuitemId=='openAdaptor') {
        var text = planExplainTree.getItemText(treeitemId);
        var l = text.split('(')[1];
        var url = l.split(')')[0];
        GoToAdaptor(url);
    }
    if(menuitemId=='openSources') {
        if(adaptors_count>0) {
           planExplainTree.selectItem(adaptors[0]);
           planExplainTree.focusItem(adaptors[0]);
        } else {
           alert('References to this adaptor were not found');
        }
    }
    if(menuitemId=='openFunction') {
        var text = planExplainTree.getItemText(treeitemId);
        GoToFunction(text);
    }
    if(menuitemId=='openCalls') {        
        if(functions_count>0) {
           planExplainTree.selectItem(functions[0]);
           planExplainTree.focusItem(functions[0]);
        } else {
           alert('References to this function were not found');
        }
    }
    if(menuitemId=='next')
        NextReference();
    if(menuitemId=='prev')
        PrevReference();
    if(menuitemId=='nextSource')
        NextAdaptorReference();
    if(menuitemId=='prevSource')
        PrevAdaptorReference();
    if(menuitemId=='nextCall')
        NextFunctionCall();
    if(menuitemId=='prevCall')
        PrevFunctionCall();

    var split = menuitemId.split('-');
    if(split[0]=='defines') {
        GoToDefinition(split[1]);
    }
    if(split[0]=='references') {
        GoToDefinition(split[1]);        
    }

    if(menuitemId=='8px') {
       getStyleClass('standartTreeRow').style.fontSize='8px';
       getStyleClass('selectedTreeRow').style.fontSize='8px';
    }
    if(menuitemId=='10px') {
       getStyleClass('standartTreeRow').style.fontSize='10px';
       getStyleClass('selectedTreeRow').style.fontSize='10px';
    }
    if(menuitemId=='12px') {
       getStyleClass('standartTreeRow').style.fontSize='12px';
       getStyleClass('selectedTreeRow').style.fontSize='12px';
    }
    if(menuitemId=='16px') {
       getStyleClass('standartTreeRow').style.fontSize='16px';
       getStyleClass('selectedTreeRow').style.fontSize='16px';
    }
    if(menuitemId=='18px') {
       getStyleClass('standartTreeRow').style.fontSize='18px';
       getStyleClass('selectedTreeRow').style.fontSize='18px';
    }
    if(menuitemId=='22px') {
       getStyleClass('standartTreeRow').style.fontSize='22px';
       getStyleClass('selectedTreeRow').style.fontSize='22px';
    }
    if(menuitemId=='24px') {
       getStyleClass('standartTreeRow').style.fontSize='24px';
       getStyleClass('selectedTreeRow').style.fontSize='24px';
    }
        
}

function makeMenuForQuery(id) {
var s = "<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'>";
    
    var parentId = planExplainTree.getParentId(id);
    var temp=planExplainTree._globalIdStorageFind(parentId);
    var count = 0;
    for(var i=0;i<temp.childsCount;i++) {
         var node=temp.childNodes[i];
         if(node.label=='<b>Inputs</b>') {
           s+="<MenuItem name='References...' src='gotoFirstReference.gif' id='references' width='120px' panelWidth='120'>";
           for(var j=0;j<node.childsCount;j++) {
             var child=node.childNodes[j];
             s+="<MenuItem src='varRef22.gif' name='";
             var text = child.label.split(' ');
             if(text[3]!=null) text=text[3];
             s+=text;
             s+="' id='references-";
             s+=text;
             s+="'/>";
           }
           s+="</MenuItem>";
           count++;
         }

         if(node.label=='<b>Outputs</b>') {
           s+="<MenuItem name='Defines...' src='gotoDefinition.gif' id='defines' width='120px' panelWidth='120'>";
           for(var j=0;j<node.childsCount;j++) {
             var child=node.childNodes[j];
             s+="<MenuItem  src='var22.gif' name='";
             var text = child.label.split(' ');
             text=text[0];
             s+=text;
             s+="' id='defines-";
             s+=text;
             s+="'/>";
           }
           s+="</MenuItem>";
           count++;
         }

    }
    s+=fontSizeMenu(count > 0, true)+"</menu>";
    return s;
}


function makeMenuForConstructor(id,referenced) {
var s = "<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'>";
    if(referenced) {
         s+="<MenuItem name='Go to first reference' src='where_referenced.gif' id='openReferences'/>";
    } 
    
    var temp=planExplainTree._globalIdStorageFind(id);
    var count = 0;
    for(var i=0;i<temp.childsCount;i++) {
         var node=temp.childNodes[i];
         if(node.label=='<b>Inputs</b>') {
           s+="<MenuItem name='References...' src='gotoFirstReference.gif' id='references' width='120px' panelWidth='120'>";
           for(var j=0;j<node.childsCount;j++) {
             var child=node.childNodes[j];
             s+="<MenuItem src='varRef22.gif' name='";
             var text = child.label.split(' ');
             if(text[3]!=null) text=text[3];
             s+=text;
             s+="' id='references-";
             s+=text;
             s+="'/>";
           }
           s+="</MenuItem>";
           count++;
         }

    }
    s+=fontSizeMenu(count > 0, false)+"</menu>";
    return s;
}


function onContextMenuShow(id)
{

   planExplainTree.focusItem(id);
   planExplainTree.selectItem(id);
   

   var toRemove = new Array();
   var len = planExplainMenu.menu.gitemsCount;
   for(var i=0; i<len;i++) {
       toRemove[i]=planExplainMenu.menu.gitems[i].id;
   }
   for(var i=0; i<len;i++) {
       planExplainMenu.menu.removeItem(toRemove[i]);
   }
   var image = planExplainTree.getItemImage(id);


   if(image=='varRef.gif') {
       var name = planExplainTree.getItemText(id);
       var split=name.split(' ');
       if(split[3]!=null) name = split[3];
       else name = split[0];
       FindReferences(name);
       for(var i=0;i<usage_count;i++) {
           if(usage[i]==id) {
               usage_index = i;
               break;
           }
       }
       var bNext = false;
       var bPrev = false;
       if(usage_count>1 && usage_index<usage_count-1) {
          bNext = true;
       }
       if(usage_index>0) {
          bPrev = true;
       }

       if(!bNext && !bPrev) {
           if(HasDefinition(name)) {
               planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to definition' src='where_defined.gif' id='openDefinition'/>"+fontSizeMenu(true, false)+"</menu>");
           } else {
               planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'>"+fontSizeMenu(false, false)+"</menu>");
           }
       } 
       if(bNext && !bPrev) {
           if(HasDefinition(name)) {
               planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to definition' src='where_defined.gif' id='openDefinition'/><MenuItem name='Go to next reference' src='next_reference.gif' id='next'/>"+fontSizeMenu(true, false)+"</menu>");
           } else {
               planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to next reference' src='next_reference.gif' id='next'/>"+fontSizeMenu(true, false)+"</menu>");
           }
       } 
       if(!bNext && bPrev) {
           if(HasDefinition(name)) {
               planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to definition' src='where_defined.gif' id='openDefinition'/><MenuItem name='Go to previous reference' src='prev_reference.gif' id='prev'/>"+fontSizeMenu(true, false)+"</menu>");
           } else {
               planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to previous reference' src='prev_reference.gif' id='prev'/>"+fontSizeMenu(true, false)+"</menu>");
           }
       } 
       if(bNext && bPrev) {
           if(HasDefinition(name)) {
               planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to definition' src='where_defined.gif' id='openDefinition'/><MenuItem name='Go to next reference' src='next_reference.gif' id='next'/><MenuItem name='Go to previous reference' src='prev_reference.gif' id='prev'/>"+fontSizeMenu(true, false)+"</menu>");
           } else {
               planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to next reference' src='next_reference.gif' id='next'/><MenuItem name='Go to previous reference' src='prev_reference.gif' id='prev'/>"+fontSizeMenu(true, false)+"</menu>");
           }
       } 
   } else {
       if(image=='var.gif') {
           FindReferences(planExplainTree.getItemText(id));
           if(usage_count>0) {
               planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to first reference' src='where_referenced.gif' id='openReferences'/>"+fontSizeMenu(true, false)+"</menu>");
           } else {
               planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'>"+fontSizeMenu(false, false)+"</menu>");
           }
       } else {
           if(image=="xquery.gif" || image=='sql.gif') {
               var s=makeMenuForQuery(id);               
               if(s!='') planExplainMenu.menu.loadXMLString(s);
           } else {
               if(image=="dbSource.gif") {
                   var text = planExplainTree.getItemText(id);
                   var l = text.split('(')[1];
                   var url = l.split(')')[0];
                   FindAdaptorUsage(url);
                   for(var i=0;i<adaptors_count;i++) {
                       if(adaptors[i]==id) {
                           adaptors_index = i;
                           break;
                       }
                   }
                   var bNext = false;
                   var bPrev = false;
                   if(adaptors_count>1 && adaptors_index<adaptors_count-1) {
                      bNext = true;
                   } 
                   if(adaptors_index>0) {
                       bPrev = true;
                   }

                   if(!bNext && !bPrev) {
                       planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to definition' src='where_defined.gif' id='openAdaptor'/>"+fontSizeMenu(true, false)+"</menu>");
                   }  
                   if(bNext && !bPrev) {
                       planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to definition' src='where_defined.gif' id='openAdaptor'/><MenuItem name='Go to next reference' src='next_reference.gif' id='nextSource'/>"+fontSizeMenu(true, false)+"</menu>");
                   } 
                   if(!bNext && bPrev) {
                       planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to definition' src='where_defined.gif' id='openAdaptor'/><MenuItem name='Go to previous reference' src='prev_reference.gif' id='prevSource'/>"+fontSizeMenu(true, false)+"</menu>");
                   } 
                   if(bNext && bPrev) {
                       planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to definition' src='where_defined.gif' id='openAdaptor'/><MenuItem name='Go to next reference' src='next_reference.gif' id='nextSource'/><MenuItem name='Go to previous reference' src='prev_reference.gif' id='prevSource'/>"+fontSizeMenu(true, false)+"</menu>");
                   } 
               } else {
                    if(image=="adaptor.gif") {
                        var text=planExplainTree.getItemText(id);
                        FindAdaptorUsage(text);
                        if(adaptors_count>0) {
                            planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to first reference' src='where_referenced.gif' id='openSources'/>"+fontSizeMenu(true, false)+"</menu>");
                        } else {
                            planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'>"+fontSizeMenu(false, false)+"</menu>");
                        }
                    } else {
                        if(image=="function.gif") {
                            var text=planExplainTree.getItemText(id);
                            FindFunctionCalls(text);
                            if(functions_count>0) {
                                planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to first reference' src='where_referenced.gif' id='openCalls'/>"+fontSizeMenu(true, false)+"</menu>");
                            } else {
                                planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'>"+fontSizeMenu(false, false)+"</menu>");
                            }
                        } else {
                            if(image=="call.gif") {
                                var text = planExplainTree.getItemText(id);
                                FindFunctionCalls(text);
                                for(var i=0;i<functions_count;i++) {
                                   if(functions[i]==id) {
                                      functions_index = i;
                                      break;
                                   }
                                }
                                var bNext = false;
                                var bPrev = false;
                                if(functions_count>1 && functions_index<functions_count-1) {
                                   bNext = true;
                                } 
                                if(functions_index>0) {
                                   bPrev = true;
                                }

                                if(!bNext && !bPrev) {
                                    planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to definition' src='where_defined.gif' id='openFunction'/>"+fontSizeMenu(true, false)+"</menu>");
                                }  
                                if(bNext && !bPrev) {
                                    planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to definition' src='where_defined.gif' id='openFunction'/><MenuItem name='Go to next reference' src='next_reference.gif' id='nextCall'/>"+fontSizeMenu(true, false)+"</menu>");
                                } 
                                if(!bNext && bPrev) {
                                    planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to definition' src='where_defined.gif' id='openFunction'/><MenuItem name='Go to previous reference' src='prev_reference.gif' id='prevCall'/>"+fontSizeMenu(true, false)+"</menu>");
                                } 
                                if(bNext && bPrev) {
                                    planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'><MenuItem name='Go to definition' src='where_defined.gif' id='openFunction'/><MenuItem name='Go to next reference' src='next_reference.gif' id='nextCall'/><MenuItem name='Go to previous reference' src='prev_reference.gif' id='prevCall'/>"+fontSizeMenu(true, false)+"</menu>");
                                } 
                            } else {
                                if(image=="constructor.gif") {
                                    FindReferences(planExplainTree.getItemText(id));
                                    var s=makeMenuForConstructor(id,usage_count>0);               
                                    if(s!='') planExplainMenu.menu.loadXMLString(s);
                                } else {
                                    planExplainMenu.menu.loadXMLString("<menu absolutePosition='auto' mode='popup' maxItems='8' globalCss='contextMenu' globalSecondCss='contextMenu' globalTextCss='contextMenuItem'>"+fontSizeMenu(false, false)+"</menu>");
                                }
                            }
                        }
                    }
               }
           }
       }
   }
   
}


function PlanExplainTreeFromHTML(obj){
 if(typeof(obj)!="object")
     obj=document.getElementById(obj);
 var n=obj;
 var id=n.id;

 var cont="";
 for (var j=0; j<obj.childNodes.length; j++) { 
   if (obj.childNodes[j].nodeType=="1") {  
     if (obj.childNodes[j].tagName=="XMP") {  
       var cHead=obj.childNodes[j];
       for (var m=0; m<cHead.childNodes.length; m++) {
         cont+=cHead.childNodes[m].data;  
       }
     }  
     else if (obj.childNodes[j].tagName.toLowerCase()=="ul")
       cont=dhx_li2trees(obj.childNodes[j],new Array(),0);  
     break;  
   } 
 }  
 obj.innerHTML="";
 var strBrowser = navigator.appName;
 if(strBrowser=="Netscape") {
    obj.style.height="500pt";
    obj.style.width="98%";
 }

 planExplainTree=new dhtmlXTreeObject(obj,"100%","100%",0);
 planExplainMenu=new dhtmlXContextMenuObject('120',0,'menu');			
 planExplainMenu.menu.setGfxPath('./imgs/');						
 planExplainMenu.setContextMenuHandler(onContextMenuSelect); 
 planExplainMenu.setOnShowMenuHandler(onContextMenuShow);

 planExplainTree.enableContextMenu(planExplainMenu);
 planExplainTree.enableMultiLineItems("100%");
 var z_all=new Array();
 for(b in planExplainTree)
   z_all[b.toLowerCase()]=b;

 var atr=obj.attributes;
 for(var a=0;a<atr.length;a++)
     if((atr[a].name.indexOf("set")==0)||(atr[a].name.indexOf("enable")==0)) {
         var an=atr[a].name;
         if(!planExplainTree[an])
             an=z_all[atr[a].name];
         planExplainTree[an].apply(planExplainTree,atr[a].value.split(","));
     }

 if(typeof(cont)=="object") {
     t.XMLloadingWarning=1;
     for(var i=0;i<cont.length;i++)
         planExplainTree.insertNewItem(cont[i][0],(i+1),cont[i][1]);
     planExplainTree.XMLloadingWarning=0;
     planExplainTree.lastLoadedXMLId=0;
     planExplainTree._redrawFrom(t);
 }
 else
     planExplainTree.loadXMLString("<tree id='0'>"+cont+"</tree>");
 window[id]=planExplainTree;
 var root=planExplainTree._globalIdStorageFind(planExplainTree.rootId);
 planExplainTree.openAllItemsDynamic();
 for(var i=0;i<root.childsCount-1;i++)
 {
   planExplainTree.closeItem(root.childNodes[i].id);
 }
 return planExplainTree;
}

function getStyleClass (className) {
    for (var s = 0; s < document.styleSheets.length; s++) {
       if(document.styleSheets[s].rules) {
           for (var r = 0; r < document.styleSheets[s].rules.length; r++) {
		if (document.styleSheets[s].rules[r].selectorText == '.' + className)
		{
			return document.styleSheets[s].rules[r];
		}
	   }
        }
	else if(document.styleSheets[s].cssRules) {
		for (var r = 0; r < document.styleSheets[s].cssRules.length; r++)
		{
			if (document.styleSheets[s].cssRules[r].selectorText == '.' + className)
				return document.styleSheets[s].cssRules[r];
		}
	}
     }	
     return null;
}


function definitionBtnClicked() {
    var img = planExplainTree.getItemImage(planExplainTree.getSelectedItemId());
    if(img=='varRef.gif') {
        GoToDefinition(planExplainTree.getItemText(planExplainTree.getSelectedItemId()));
    }
    if(img=='dbSource.gif') {
        var text = planExplainTree.getItemText(planExplainTree.getSelectedItemId());
        var l = text.split('(')[1];
        var url = l.split(')')[0];
        GoToAdaptor(url);
    }
    if(img=='call.gif') {
        var text = planExplainTree.getItemText(planExplainTree.getSelectedItemId());
        GoToFunction(text);
    }
}

function firstReferenceBtnClicked() {
    var img = planExplainTree.getItemImage(planExplainTree.getSelectedItemId());
    if(img=='var.gif' || img=='constructor.gif') {
        FindReferences(planExplainTree.getItemText(planExplainTree.getSelectedItemId()));  
        if(usage_count>0) {
           planExplainTree.selectItem(usage[0]);
           planExplainTree.focusItem(usage[0]);
        } else {
           alert('References to this variable were not found');
        }
    }
    if(img=='adaptor.gif') {
        var url = planExplainTree.getItemText(planExplainTree.getSelectedItemId());
        FindAdaptorUsage(url);
        if(adaptors_count>0) {
           planExplainTree.selectItem(adaptors[0]);
           planExplainTree.focusItem(adaptors[0]);
        } else {
           alert('References to this adaptor were not found');
        }
    }
    if(img=='function.gif') {
        var name = planExplainTree.getItemText(planExplainTree.getSelectedItemId());
        FindFunctionCalls(name);
        if(functions_count>0) {
           planExplainTree.selectItem(functions[0]);
           planExplainTree.focusItem(functions[0]);
        } else {
           alert('References to this function were not found');
        }
    }
}

function prevReferenceBtnClicked() {
    var id = planExplainTree.getSelectedItemId();
    var img = planExplainTree.getItemImage(id);
    if(img=='varRef.gif') {
       var name = planExplainTree.getItemText(id);
       var split=name.split(' ');
       if(split[3]!=null) name = split[3];
       else name = split[0];
       FindReferences(name);
       for(var i=0;i<usage_count;i++) {
           if(usage[i]==id) {
               usage_index = i;
               break;
           }
       }
       var bPrev = false;
       if(usage_index>0) {
          bPrev = true;
       }
       if(bPrev) PrevReference();
    }
    if(img=='dbSource.gif') {
       var text = planExplainTree.getItemText(id);
       var l = text.split('(')[1];
       var url = l.split(')')[0];
       FindAdaptorUsage(url);
       for(var i=0;i<adaptors_count;i++) {
         if(adaptors[i]==id) {
              adaptors_index = i;
              break;
         }
       }
       var bPrev = false;
       if(adaptors_index>0) {
            bPrev = true;
       }
       if(bPrev) PrevAdaptorReference();
    }
    if(img=='call.gif') {
       var text = planExplainTree.getItemText(id);
       FindFunctionCalls(text);
       for(var i=0;i<functions_count;i++) {
         if(functions[i]==id) {
              functions_index = i;
              break;
         }
       }
       var bPrev = false;
       if(functions_index>0) {
            bPrev = true;
       }
       if(bPrev) PrevFunctionCall();
    }
}

function nextReferenceBtnClicked() {
    var id = planExplainTree.getSelectedItemId();
    var img = planExplainTree.getItemImage(id);
    if(img=='varRef.gif') {
       var name = planExplainTree.getItemText(id);
       var split=name.split(' ');
       if(split[3]!=null) name = split[3];
       else name = split[0];
       FindReferences(name);
       for(var i=0;i<usage_count;i++) {
           if(usage[i]==id) {
               usage_index = i;
               break;
           }
       }
       var bNext = false;
       if(usage_count>1 && usage_index<usage_count-1) {
          bNext = true;
       }
       if(bNext) NextReference();
    }
    if(img=='dbSource.gif') {
       var text = planExplainTree.getItemText(id);
       var l = text.split('(')[1];
       var url = l.split(')')[0];
       FindAdaptorUsage(url);
       for(var i=0;i<adaptors_count;i++) {
         if(adaptors[i]==id) {
              adaptors_index = i;
              break;
         }
       }
       var bNext = false;
       if(adaptors_count>1 && adaptors_index<adaptors_count-1) {
            bNext = true;
       } 
       if(bNext) NextAdaptorReference();
    }
    if(img=='call.gif') {
       var text = planExplainTree.getItemText(id);
       FindFunctionCalls(text);       
       for(var i=0;i<functions_count;i++) {
         if(functions[i]==id) {
              functions_index = i;
              break;
         }
       }
       var bNext = false;
       if(functions_count>1 && functions_index<functions_count-1) {
            bNext = true;
       } 
       if(bNext) NextFunctionCall();
    }
}

dhtmlXTreeObject.prototype._selectItem=function(node,e){
 if((!this._amsel)||(!e)||((!e.ctrlKey)&&(!e.shiftKey)))
     this._unselectItems();
 if((node.i_sel)&&(this._amsel)&&(e)&&(e.ctrlKey))
     this._unselectItem(node);
 else
     if((!node.i_sel)&&((!this._amselS)||(this._selected.length==0)||(this._selected[0].parentObject==node.parentObject)))
 if((this._amsel)&&(e)&&(e.shiftKey)&&(this._selected.length!=0)&&(this._selected[this._selected.length-1].parentObject==node.parentObject)){
     var a=this._getIndex(this._selected[this._selected.length-1]);
     var b=this._getIndex(node);
     if(b<a){var c=a;a=b;b=c;}
     for(var i=a;i<=b;i++)
     if(!node.parentObject.childNodes[i].i_sel)
     this._markItem(node.parentObject.childNodes[i]);
 }
 else
     this._markItem(node);

 updateToolbar();

}


function updateToolbar() {
   var id = planExplainTree.getSelectedItemId();
   var image = planExplainTree.getItemImage(id);

   var gotoDefinition     = document.images['definition'];
   var gotoFirstReference = document.images['reference'];
   var gotoNextReference  = document.images['next_reference'];
   var gotoPrevReference  = document.images['prev_reference'];

   gotoDefinition.src      ='./imgs/gotoDefinitionBtnDisabled.gif';
   gotoFirstReference.src  ='./imgs/gotoFirstReferenceBtnDisabled.gif';
   gotoNextReference.src   ='./imgs/gotoNextReferenceBtnDisabled.gif';
   gotoPrevReference.src   ='./imgs/gotoPrevReferenceBtnDisabled.gif';

   if(image=='varRef.gif') {
       var name = planExplainTree.getItemText(id);
       var split=name.split(' ');
       if(split[3]!=null) name = split[3];
       else name = split[0];
       FindReferences(name);
       for(var i=0;i<usage_count;i++) {
           if(usage[i]==id) {
               usage_index = i;
               break;
           }
       }
       var bNext = false;
       var bPrev = false;
       if(usage_count>1 && usage_index<usage_count-1) {
          bNext = true;
       }
       if(usage_index>0) {
          bPrev = true;
       }
       gotoDefinition.src      ='./imgs/gotoDefinitionBtn.gif';
       if(bNext) gotoNextReference.src   ='./imgs/gotoNextReferenceBtn.gif';
       if(bPrev) gotoPrevReference.src   ='./imgs/gotoPrevReferenceBtn.gif';      
   } else {
       if(image=='var.gif') {
           FindReferences(planExplainTree.getItemText(id));
           if(usage_count>0) {
              gotoFirstReference.src  ='./imgs/gotoFirstReferenceBtn.gif';            
           }
       } else {
           if(image=="dbSource.gif") {
               var text = planExplainTree.getItemText(id);
               var l = text.split('(')[1];
               var url = l.split(')')[0];
               FindAdaptorUsage(url);
               for(var i=0;i<adaptors_count;i++) {
                   if(adaptors[i]==id) {
                       adaptors_index = i;
                       break;
                   }
               }
               var bNext = false;
               var bPrev = false;
               if(adaptors_count>1 && adaptors_index<adaptors_count-1) {
                  bNext = true;
               } 
               if(adaptors_index>0) {
                   bPrev = true;
               }
               gotoDefinition.src      ='./imgs/gotoDefinitionBtn.gif';
               if(bNext) gotoNextReference.src   ='./imgs/gotoNextReferenceBtn.gif';
               if(bPrev) gotoPrevReference.src   ='./imgs/gotoPrevReferenceBtn.gif';
           } else {
               if(image=="adaptor.gif") {
                    var text=planExplainTree.getItemText(id);
                    FindAdaptorUsage(text);
                    if(adaptors_count>0) {
                        gotoFirstReference.src  ='./imgs/gotoFirstReferenceBtn.gif';
                    }
                } else {
                    if(image=="function.gif") {
                        var text=planExplainTree.getItemText(id);
                        FindFunctionCalls(text);
                        if(functions_count>0) {
                            gotoFirstReference.src  ='./imgs/gotoFirstReferenceBtn.gif';
                        }
                    } else {
                        if(image=="call.gif") {
                            var text = planExplainTree.getItemText(id);
                            FindFunctionCalls(text);
                            for(var i=0;i<functions_count;i++) {
                               if(functions[i]==id) {
                                  functions_index = i;
                                  break;
                               }
                            }
                            var bNext = false;
                            var bPrev = false;
                            if(functions_count>1 && functions_index<functions_count-1) {
                               bNext = true;
                            } 
                            if(functions_index>0) {
                               bPrev = true;
                            }
                            gotoDefinition.src      ='./imgs/gotoDefinitionBtn.gif';
                            if(bNext) gotoNextReference.src   ='./imgs/gotoNextReferenceBtn.gif';
                            if(bPrev) gotoPrevReference.src   ='./imgs/gotoPrevReferenceBtn.gif';
                        } else {
                            if(image=="constructor.gif") {
                                FindReferences(planExplainTree.getItemText(id));
                                if(usage_count>0) {
                                    gotoFirstReference.src  ='./imgs/gotoFirstReferenceBtn.gif';            
                                }
                            }
                        }
                    }
               }
           }
       }
   }
}


 function dhtmlXMenuItemObject(id,text,width,src,className,disableImage,href,target,type){
 type=type||"a1";
 var src2="";
 var tooltip="";
 if(id.tagName=="MenuItem")
{
 type=text||"a1";
 src=id.getAttribute("src");
 src2=id.getAttribute("src2");
 text=id.getAttribute("name");
 className=id.getAttribute("className");
 disableImage=id.getAttribute("disableImage");
 width=id.getAttribute("width");
 href=id.getAttribute("href");
 target=id.getAttribute("target");
 tooltip=id.getAttribute("tooltip");
 if((tooltip!=="")&&(!tooltip))tooltip=text;
 id=id.getAttribute("id");
}
 if(id)this.id=id;
 else this.id=(new Date()).valueOf();
 
 src2=src2||src;



 this.topNod=0;
 this.action=0;
 this.persAction=0;
 this.src=src;
 this.text=text;
 this.href=href;
 this.target=target;

 this.className=className||"menuButton";
 this.textClassName="defaultMenuText";
 this.disableImage=disableImage;
 
 td=document.createElement("td");
 this.topNod=td;td.align="center";
 td.noWrap=true;
 
 td.innerHTML="<table align='left' cellpadding='0' cellspacing='0' border='0' "+(width?("width='"+width+"'"):"")+" height='100%'><tr><td width='20px' style=' "+(src?"":"display:none;")+"'><img src='"+src2+"' border='0' width='22px' height='18px'></td><td width='100%' align='left' style=' "+(src?" padding-left:2px;":"")+" overflow:hidden;' ><table width='100%' height='100%' cellpadding='0' cellspacing='0'><tr><td title='"+tooltip+"' class='"+this.textClassName+"' nowrap >"+this.text+"</td><td width='12px'><img style='display:none'></td></tr></table></td></tr></table>";
 this.imageTag=td.childNodes[0].childNodes[0].childNodes[0].childNodes[0].childNodes[0];
 this.childMenuTag=td.childNodes[0].childNodes[0].childNodes[0].childNodes[1].childNodes[0].childNodes[0].childNodes[0].childNodes[1].childNodes[0];
 this.textTag=this.childMenuTag.parentNode.parentNode.childNodes[0];

 switch(type){
 case "a1":
 this.CSSTag=td;
 this.CSSImageTag=null;
 break;
 case "a2":
 this.CSSTag=td.childNodes[0];
 this.CSSImageTag=null;
 break;
 case "a3":
 this.CSSTag=td.childNodes[0].childNodes[0].childNodes[0].childNodes[1];
 this.CSSImageTag=null;
 break;
 case "b1":
 this.CSSTag=td;
 this.CSSImageTag=this.imageTag.parentNode;
 break;
 case "b2":
 this.CSSTag=td.childNodes[0];
 this.CSSImageTag=this.imageTag.parentNode;
 break;
 case "b3":
 this.CSSTag=td.childNodes[0].childNodes[0].childNodes[0].childNodes[1];
 this.CSSImageTag=this.imageTag.parentNode;
 break;
}
 td.id="menuItem_"+this.id;
 this.CSSTag.className=this.className;
 td.objectNode=this;
 this.enable();
 return this;
}

