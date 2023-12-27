# Kirana Register Backend Service
## Problem Statement -
The Kirana Register Backend Service is a SpringBoot-based application designed to empower Kirana stores in managing their transaction registers efficiently. The service provides a comprehensive solution for daily tracking of credit and debit transactions, facilitating effective financial record-keeping.

## LLD and HDL - 

#### Database: PostgreSQL
#### Schema
##### Class: Balance
###### Fields:
1. id (UUID):
- Description: Unique identifier for each balance record.
- Annotations:
- @Id: Marks this field as the primary key.
- @GeneratedValue(generator = "uuid2"): Specifies the use of a UUID generator.
- @GenericGenerator(name = "uuid2", strategy = "uuid2"): Defines the UUID generation strategy.
- @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false): Maps this field to the "id" column in the database table.
userID (UUID):

2. Description: Unique identifier for the user associated with the balance.
- Annotations:
- @Column(name = "user_id", nullable = false): Maps this field to the "user_id" column in the database table.

3. assetType (String):
- Description: Type of asset associated with the balance.
- Annotations:
- @Column(name = "asset_type", nullable = false): Maps this field to the "asset_type" column in the database table.
4. balance (float):

- Description: The balance amount associated with the user and asset type.
- Annotations:
- @Column(name = "balance", nullable = false): Maps this field to the "balance" column in the database table.
5. createdAt (Date):
- Description: Date and time when the balance record was created.
- Annotations:
- @CreationTimestamp: Automatically sets the field value to the current timestamp during entity creation.
- @Temporal(TemporalType.TIMESTAMP): Specifies the temporal type for the date.
6. updatedAt (Date):
- Description: Date and time when the balance record was last updated.
- Annotations:
- @UpdateTimestamp: Automatically updates the field value to the current timestamp during entity updates.
- @Temporal(TemporalType.TIMESTAMP): Specifies the temporal type for the date.
- Methods:
Getter and setter methods for each field.
Usage:
This class is intended to be used with a JPA implementation (such as Hibernate) to interact with a database table named "balance." It represents the structure and behavior of the balance entities in the system.


#### Class: Transactions
##### Fields:
1. id (UUID):

- Description: Unique identifier for each transaction record.
- Annotations:
- @Id: Marks this field as the primary key.
- @GeneratedValue(generator = "uuid2"): Specifies the use of a UUID generator.
- @GenericGenerator(name = "uuid2", strategy = "uuid2"): Defines the UUID generation strategy.
- @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false): Maps this field to the "id" column in the database table.
2. userID (UUID):

- Description: Unique identifier for the user associated with the transaction.
- Annotations:
- @Column(name = "user_id", nullable = false): Maps this field to the "user_id" column in the database table.
3. TxnId (UUID):

- Description: Unique identifier for each transaction.
- Annotations:
- @Column(name = "txn_id", nullable = false): Maps this field to the "txn_id" column in the database table.
4. assetType (String):

- Description: Type of asset associated with the transaction.
- Annotations:
- @Column(name = "asset_type", nullable = false): Maps this field to the "asset_type" column in the database table.
5. transactionType (String):

- Description: Type of the transaction (e.g., buy, sell).
- Annotations:
- @Column(name = "transaction_type", nullable = false): Maps this field to the "transaction_type" column in the database table.
6. price (float):

- Description: The price of the asset in the transaction.
- Annotations:
- @Column(name = "price", nullable = false): Maps this field to the "price" column in the database table.
7. quantity (float):

- Description: The quantity of the asset in the transaction.
- Annotations:
- @Column(name = "quantity", nullable = false): Maps this field to the "quantity" column in the database table.
8. createdAt (Date):

- Description: Date and time when the transaction record was created.
- Annotations:
- @CreationTimestamp: Automatically sets the field value to the current timestamp during entity creation.
- @Temporal(TemporalType.TIMESTAMP): Specifies the temporal type for the date.
9. updatedAt (Date):

- Description: Date and time when the transaction record was last updated.
- Annotations:
- @UpdateTimestamp: Automatically updates the field value to the current timestamp during entity updates.
- @Temporal(TemporalType.TIMESTAMP): Specifies the temporal type for the date.
- Constructors:
- Default Constructor: An empty constructor.
- Parameterized Constructor: Takes parameters for userID, TxnId, assetType, price, and quantity, setting createdAt and updatedAt to the current date.

##### Class: Users
###### Fields:
1. id (UUID):

- Description: Unique identifier for each user record.
- Annotations:
- @Id: Marks this field as the primary key.
- @GeneratedValue(generator = "uuid2"): Specifies the use of a UUID generator.
- @GenericGenerator(name = "uuid2", strategy = "uuid2"): Defines the UUID generation strategy.
- @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false): Maps this field to the "id" column in the database table.
2. firstName (String):

- Description: First name of the user.
- Annotations:
- @Column(name = "first_name", nullable = false): Maps this field to the "first_name" column in the database table.
3. lastName (String):

- Description: Last name of the user.
- Annotations:
- @Column(name = "last_name", nullable = false): Maps this field to the "last_name" column in the database table.
4. emailId (String):

- Description: Email address of the user.
- Annotations:
- @Column(name = "email_address", nullable = false): Maps this field to the "email_address" column in the database table.
5. isDeleted (boolean):

- Description: Indicates whether the user is marked as deleted (soft delete).
- Default: false.
6. createdAt (Date):

- Description: Date and time when the user record was created.
- Annotations:
- @Temporal(TemporalType.TIMESTAMP): Specifies the temporal type for the date.
- @Column(name = "created_at", updatable = false): Maps this field to the "created_at" column in the database table.
7. updatedAt (Date):

- Description: Date and time when the user record was last updated.
- Annotations:
- @Temporal(TemporalType.TIMESTAMP): Specifies the temporal type for the date.
- @Column(name = "updated_at"): Maps this field to the "updated_at" column in the database table.



### Functional Requirement:-
1. Need an API for recording transactions. Transactions can be recorded in both INR
   and USD. Refer to this API for currency conversion -
   https://api.fxratesapi.com/latest.
2. An API for displaying transactions with the capability to group them for daily reports.
3. You can use a database of your choice SQL or NoSQL.
4. Tech Stack - JAVA and SpringBoot





