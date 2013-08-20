(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: Remove all PRGS stock holdings for Minollo :)
ddtek:sql-delete(collection("holdings")/holdings[userid = "Minollo" and stockticker = "PRGS"]) 
