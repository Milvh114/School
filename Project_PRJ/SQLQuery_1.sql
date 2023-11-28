-- CREATE DATABASE Demo_ShopOnline

-- USE Demo_ShopOnline

-- create table

CREATE TABLE Admin (
    id int NOT NULL PRIMARY KEY,
    cname VARCHAR(55),
)

CREATE TABLE Product (
    pid int NOT NULL PRIMARY KEY,
    pname VARCHAR(55),
    description VARCHAR(255),
    price FLOAT,
    quantity INT CHECK (quantity>=0),
    image VARCHAR(255),
    cID int FOREIGN KEY REFERENCES Categories(cid)
)

CREATE TABLE Account (
    uid int NOT NULL PRIMARY KEY,
    username VARCHAR(55) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role INT NOT NULL,
)

create table [Cart] (
[uid] [int] FOREIGN KEY REFERENCES Account(uid),
[pid] [int] FOREIGN KEY REFERENCES Product(pid),
[amount] [int]) not null;

-- end create table

GO
INSERT [dbo].[Account] ([uid], [username], [password], [isAdmin], [isSell]) VALUES (1,'admin','123',1,0)
INSERT [dbo].[Account] ([uid], [username], [password], [isAdmin], [isSell]) VALUES (2,'minh','1234',0,0)
INSERT [dbo].[Account] ([uid], [username], [password], [isAdmin], [isSell]) VALUES (3,'ha','321',0,0)
INSERT [dbo].[Account] ([uid], [username], [password], [isAdmin], [isSell]) VALUES (4,'sell','123',0,1)

go
INSERT [dbo].[Product] ([pid], [pname], [description], [price], [quantity], [image], [cID]) VALUES (11,'Z.N.E. PANTS','Let your style match your lifestyle in these premium, high-rise pants from the adidas Z.N.E. collection. Even when you''re on the go, four-way stretch material lets you move without restriction.',199.99,3,'Img_PRJ/pants1.avif',2)
INSERT [dbo].[Product] ([pid], [pname], [description], [price], [quantity], [image], [cID]) VALUES (3,'áo THE RUN SHORTS','Whether you''re hitting the pavement with your community or on your own, there''s nothing quite like the joyful rush of a runner''s high.',100,2,'Img_PRJ/shorts_adidas3.avif',3)
INSERT [dbo].[Product] ([pid], [pname], [description], [price], [quantity], [image], [cID]) VALUES (1,'Nike Sportswear Sport Essentials','Whether you''re running to the nearest food truck or diving into your favourite swimming hole, the Nike Sportswear Sport Essentials Lined Flow Shorts are a good idea.',129,3,'https://onoff.vn/media/catalog/product/cache/ecd9e5267dd6c36af89d5c309a4716fc/18TS22S137.jpg',1)
INSERT [dbo].[Product] ([pid], [pname], [description], [price], [quantity], [image], [cID]) VALUES (1002,'Modern Cotton Bikini','A Calvin Klein icon. This modern cotton bikini is the definition of effortless. Made with super soft and supple cotton stretch for all day comfort. ',13.2,111,'Img_PRJ/under2.jpeg',4)

GO
INSERT [dbo].[Categories] ([cid], [cname]) VALUES (1,'T-Shirt' )
INSERT [dbo].[Categories] ([cid], [cname]) VALUES (2,'Jeans')
INSERT [dbo].[Categories] ([cid], [cname]) VALUES (3,'Shorts')
INSERT [dbo].[Categories] ([cid], [cname]) VALUES (4,'Underwear')

