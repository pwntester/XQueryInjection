(: Copyright(c) 2003-2009 DataDirect Technologies. All rights reserved. :)

(: Upload information stored in new-users.xml to the database, shredding XML data into
   "users" and "holdings" tables
:)
for $newUser in doc("new-users.xml")/new-users/user
return (
    ddtek:sql-insert("users",
            "userid", $newUser/id, "firstname", $newUser/first-name,
            "lastname", $newUser/last-name, "membersince", $newUser/date),
    for $holding in $newUser/holdings/holding
    return
        ddtek:sql-insert("holdings", "userid", $newUser/id,
            "stockticker", $holding/ticker, "shares", $holding/quantity)
)

