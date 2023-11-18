print("Started Adding the Users.");
db = db.getSiblingDB("dev_db_2");
db.createUser(
    {
        user: "visitor",
        pwd: "pwd",
        roles: [
            {
                role: "readWrite",
                db: "dev_db_2"
            }
        ]
    }
);
print("End Adding the User Roles.");