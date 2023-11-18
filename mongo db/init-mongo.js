print("Started Adding the Users.");
db = db.getSiblingDB("dev_db");
db.createUser(
    {
        user: "visitor",
        pwd: "pwd",
        roles: [
            {
                role: "readWrite",
                db: "dev_db"
            }
        ]
    }
);
print("End Adding the User Roles.");