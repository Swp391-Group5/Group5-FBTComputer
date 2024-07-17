CREATE DATABASE ComputerShop

USE ComputerShop

--Create Table
	CREATE TABLE [Role] (
		RoleId INT PRIMARY KEY,
		RoleName NVARCHAR(50) NOT NULL,
		RoleStatus BIT
	);

	CREATE TABLE Admin (
		AdminID INT IDENTITY(1, 1) PRIMARY KEY,
		AdminName NVARCHAR(100),
		AdminAge INT,
		AdminEmail NVARCHAR(100),
		AdminPassword NVARCHAR(100),
		AdminGender NVARCHAR(50),
		AdminAddress NVARCHAR(255),
		AdminCity NVARCHAR(100),
		AdminAvatar VARBINARY(MAX),
		AdminPhoneNum NVARCHAR(20),
		AdminDOB DATE,
		AdminStatus BIT,
		RoleId INT FOREIGN KEY REFERENCES [Role](RoleId)
	);


	CREATE TABLE Customer (
		CustomerID INT IDENTITY(1, 1) PRIMARY KEY,
		CustomerName NVARCHAR(100),
		CustomerAge INT,
		CustomerEmail NVARCHAR(100),
		CustomerPassword NVARCHAR(100),
		CustomerGender BIT,
		CustomerAddress NVARCHAR(255),
		CustomerCity NVARCHAR(100),
		CustomerAvatar VARBINARY(MAX),
		CustomerPhoneNumber NVARCHAR(20),
		CustomerDOB DATE,
		CustomerStatus BIT,
		RoleId INT FOREIGN KEY REFERENCES Role(RoleId)
	);


	CREATE TABLE CategoryBlog (
		CategoryBlogId INT PRIMARY KEY,
		CategoryBlogName NVARCHAR(100),
		CategoryBlogStatus BIT
	);


	CREATE TABLE Blog (
		BlogId INT IDENTITY(1, 1) PRIMARY KEY,
		BlogTitle NVARCHAR(255),
		BlogUpdateDate DATE,
		BlogContent NVARCHAR(MAX),
		BlogThumbnail NVARCHAR(255),
		AdminId INT FOREIGN KEY REFERENCES Admin(AdminID),
		CategoryBlogId INT FOREIGN KEY REFERENCES CategoryBlog(CategoryBlogId),
		BlogStatus BIT,
		img NVARCHAR(255),
		[url] NVARCHAR(255),
	);

	CREATE TABLE Category (
		CategoryId INT PRIMARY KEY,
		CategoryName NVARCHAR(100),
		CategoryStatus BIT
	);
	CREATE TABLE Product (
		ProductId INT IDENTITY(1, 1) PRIMARY KEY,
		ProductName NVARCHAR(100),
		ProductDescription NVARCHAR(MAX),
		ProductPrice FLOAT,
		ProductQuantity INT,
		ProductBrand NVARCHAR(100),
		ProductImage NVARCHAR(255),
		ProductStatus BIT,
		CreateBy NVARCHAR(100),
		CreateDate DATE,
		ModifyBy NVARCHAR(100),
		ModifyDate DATE,
		CategoryId INT FOREIGN KEY REFERENCES Category(CategoryId),
		AdminID INT FOREIGN KEY REFERENCES Admin(AdminID),
		[Image1] [varchar](255) NULL,
		[Image2] [varchar](255) NULL,
		[Image3] [varchar](255) NULL,
	);

	CREATE TABLE Specification (
		SpecificationId INT IDENTITY(1, 1) PRIMARY KEY,
		Color NVARCHAR(50),
		Size NVARCHAR(50),
		[Weight] NVARCHAR(50),
		Manufacturer NVARCHAR(100),
		ProductName NVARCHAR(100),
		ProductId INT FOREIGN KEY REFERENCES Product(ProductId)
	);

	CREATE TABLE ProductImage (
		ProductImageId INT IDENTITY(1, 1) PRIMARY KEY,
		ProductId INT FOREIGN KEY REFERENCES Product(ProductId),
		Image NVARCHAR(255)
	);


	CREATE TABLE Warehouse (
		SerialNumber VARCHAR(10) PRIMARY KEY,
		ProductId INT FOREIGN KEY REFERENCES Product(ProductId)
	);

	CREATE TABLE Cart (
		CartId INT IDENTITY(1, 1) PRIMARY KEY,
		CustomerId INT NOT NULL,
		TotalCost DECIMAL(10, 2) NOT NULL,
		RoleId INT NOT NULL
	);

	CREATE TABLE CartDetail (
		CartDetailId INT IDENTITY(1, 1) PRIMARY KEY,
		ProductId INT NOT NULL,
		ProductName NVARCHAR(100),
		ProductImage NVARCHAR(255),
		NumberInCart INT,
		Price DECIMAL(10, 2),
		CartId INT NOT NULL,
		FOREIGN KEY (ProductId) REFERENCES Product(ProductId),
		FOREIGN KEY (CartId) REFERENCES Cart(CartId)
	);

	CREATE TABLE OrderHistory (
		OrderHistoryId INT IDENTITY(1, 1) PRIMARY KEY,
		Quantity INT,
		OrderDate DATE,
		[Name] NVARCHAR(255),
		Phone NVARCHAR(20),
		[Address] NVARCHAR(255),
		UserId INT,
		RoleId INT,
		TotalMoney FLOAT,
		IsSuccess NVARCHAR(10),
		OrderHistoryStatus BIT,
		PaymentMethod NVARCHAR(MAX),
	);

	CREATE TABLE OrderHistoryDetail(
		OrderHistoryDetailId INT IDENTITY(1, 1) PRIMARY KEY,
		SerialNumber VARCHAR(10),
		Price FLOAT,
		OrderHistoryId INT FOREIGN KEY REFERENCES [OrderHistory](OrderHistoryId),
		ProductId INT FOREIGN KEY REFERENCES [Product](ProductId)
	);

	CREATE TABLE [Order] (
		OrderID INT IDENTITY(1, 1) PRIMARY KEY,
		OrderStatus BIT,
		OrderHistoryId INT FOREIGN KEY REFERENCES [OrderHistory](OrderHistoryId),
	);

	CREATE TABLE OrderDetail (
		OrderDetailId INT IDENTITY(1, 1) PRIMARY KEY,
		OrderId INT FOREIGN KEY REFERENCES [Order](OrderID),
		OrderHistoryId INT FOREIGN KEY REFERENCES [OrderHistory](OrderHistoryId),
		OrderHistoryDetailId INT FOREIGN KEY REFERENCES [OrderHistoryDetail](OrderHistoryDetailId)
	);

	CREATE TABLE Warranty (
		WarrantyId INT IDENTITY(1, 1) PRIMARY KEY,
		ProductName NVARCHAR(100),
		CustomerName NVARCHAR(100),
		PhoneNumber NVARCHAR(20),
		AdminName NVARCHAR(100),
		ProductStatus NVARCHAR(50),
		WarrantyImage NVARCHAR(255),
		WarrantyPeriod DATE,
		IsExpired BIT,
		OrderId INT FOREIGN KEY REFERENCES [Order](OrderID),
		AdminID INT FOREIGN KEY REFERENCES Admin(AdminID)
	);

	CREATE TABLE Feedback (
		FeedbackId INT IDENTITY(1, 1) PRIMARY KEY,
		UserName NVARCHAR(255),
		UserId INT,
		RoleId INT,
		StarRate INT CHECK (StarRate BETWEEN 1 AND 5),
		FeedbackDescription NVARCHAR(MAX),
		FeedbackImage VARBINARY(MAX),
		FeedbackDate DATE,
		FeedbackStatus BIT,
		OrderId INT FOREIGN KEY REFERENCES [Order](OrderID),
		ProductId INT FOREIGN KEY REFERENCES Product(ProductID)
	);


--Reset Identity
DBCC CHECKIDENT ('admin', RESEED, 1);
DBCC CHECKIDENT ('blog', RESEED, 1);
DBCC CHECKIDENT ('customer', RESEED, 1);
DBCC CHECKIDENT ('order', RESEED, 1);
DBCC CHECKIDENT ('orderdetail', RESEED, 1);
DBCC CHECKIDENT ('orderhistory', RESEED, 1);
DBCC CHECKIDENT ('orderhistorydetail', RESEED, 1);
DBCC CHECKIDENT ('product', RESEED, 1);
DBCC CHECKIDENT ('productimage', RESEED, 1);
DBCC CHECKIDENT ('specification', RESEED, 1);
DBCC CHECKIDENT ('warranty', RESEED, 1);
DBCC CHECKIDENT ('feedback', RESEED, 1);
DBCC CHECKIDENT ('cart', RESEED, 1);
DBCC CHECKIDENT ('cartdetail', RESEED, 1);


--Insert
INSERT INTO [Role] (RoleId, RoleName, RoleStatus)
VALUES 
(1,'Customer', 1),
(2,'Admin', 1); 

	INSERT INTO [Customer]
    (CustomerName, CustomerAge, CustomerEmail, CustomerPassword, CustomerGender, CustomerAddress, CustomerAvatar, CustomerPhoneNumber, CustomerDOB, CustomerStatus, CustomerCity, RoleId)
VALUES
    ('Nguyen Van A', 25, 'anvhe000000@fpt.edu.vn', '7UhYue9lxAqVF7lTCKevsFlBF+c=', 1, '123 Nam Tu Liem', null, '0123456789', '1999-05-10', 1, 'Ho Chi Minh City', 1),
    ('Nguyen Duc Hung', 20, 'furycap47@gmail.com', '7UhYue9lxAqVF7lTCKevsFlBF+c=', 0, '1/66/36, Hoan Kiem', null, '0234567891', '2004-01-30', 1, 'Hanoi City', 1);

INSERT INTO [Admin] 
    (AdminName, AdminAge, AdminEmail, AdminPassword, AdminGender, AdminAddress, AdminAvatar, AdminPhoneNum, AdminDOB, AdminStatus, AdminCity, RoleId)
VALUES
    ('Alice Johnson', 34, 'alice.johnson@example.com', '7UhYue9lxAqVF7lTCKevsFlBF+c=', 1, '123 Elm Street', null, '0987654321', '1989-05-15', 1, 'New York', 2),
    ('Bob Smith', 29, 'bob.smith@example.com', '7UhYue9lxAqVF7lTCKevsFlBF+c=', 0, '456 Oak Avenue', null, '0912345678', '1994-11-22', 1, 'Los Angeles', 2);


	INSERT INTO Category (CategoryId, CategoryName, CategoryStatus) 
VALUES 
(1, 'Laptop', 1),
(2, 'Computer Mice', 1),
(3, 'Keyboard', 1),
(4, 'Headphone', 1);

	SET IDENTITY_INSERT Product ON
INSERT [dbo].[Product] ([ProductId], [ProductName], [ProductDescription], [ProductPrice], [ProductQuantity], [ProductBrand], [ProductImage], [ProductStatus], [CreateBy], [CreateDate], [ModifyBy], [ModifyDate], [CategoryId], [AdminID], [Image1], [Image2], [Image3]) VALUES (1, N'Laptop Asus ExpertBook B1400CEAE BV3186W', N'The Asus Expert Book B1400CEAE BV3186W laptop is a high-quality office laptop with impressive performance from an 11th generation CPU and ample storage for stable processing.', 8490000, 3, N'Asus', N'https://product.hstatic.net/200000420363/product/laptop-asus-expertbook-b1400ceae-bv3186w_14a86dbdbecd4dc99d3c860e82827cc1_master.png', 1, N'Quan Do', CAST(N'2024-05-20' AS Date), N'Quan Do', CAST(N'2024-05-20' AS Date), 1, 1, N'https://cdn2.cellphones.com.vn/x/media/catalog/product/l/a/laptop-asus-expertbook-b1400ceae-bv3186w.jpg', N'https://cdn2.cellphones.com.vn/x/media/catalog/product/l/a/laptop-asus-expertbook-b1400ceae-bv3186w_1_.jpg', N'https://cdn2.cellphones.com.vn/x/media/catalog/product/l/a/laptop-asus-expertbook-b1400ceae-bv3186w_5_.jpg')
INSERT [dbo].[Product] ([ProductId], [ProductName], [ProductDescription], [ProductPrice], [ProductQuantity], [ProductBrand], [ProductImage], [ProductStatus], [CreateBy], [CreateDate], [ModifyBy], [ModifyDate], [CategoryId], [AdminID], [Image1], [Image2], [Image3]) VALUES (2, N'Laptop Asus VivoBook 14 OLED M1405YA KM047W', N'Shine bright with the world with the powerful ASUS Vivobook 14 OLED, a laptop packed with many features including a vibrant OLED display, and cinema-grade DCI-P3 color gamut.', 14390000, 3, N'Asus', N'https://vn.store.asus.com/media/catalog/product/cache/31a3e9bc5cea0340b4f268573c7bdbfd/m/1/m1405ya-km047w-1.png', 1, N'Thong Minh', CAST(N'2024-05-20' AS Date), N'Thong Minh', CAST(N'2024-05-20' AS Date), 1, 2, N'https://cdn2.cellphones.com.vn/x/media/catalog/product/t/e/text_ng_n_12__1_51.png', N'https://cdn2.cellphones.com.vn/x/media/catalog/product/t/e/text_ng_n_17__1_37.png', N'https://cdn2.cellphones.com.vn/x/media/catalog/product/t/e/text_ng_n_16__1_42.png')
INSERT [dbo].[Product] ([ProductId], [ProductName], [ProductDescription], [ProductPrice], [ProductQuantity], [ProductBrand], [ProductImage], [ProductStatus], [CreateBy], [CreateDate], [ModifyBy], [ModifyDate], [CategoryId], [AdminID], [Image1], [Image2], [Image3]) VALUES (3, N'ASUS ROG Strix SCAR 17 G733PZ LL980W', N'The ROG Strix SCAR has always been the flagship in the ROG gaming laptop lineup, and 2023 is no different. The ROG Strix SCAR 17 returns with entirely new gaming power from the high-performance AMD Ryzen™ 9 16-core processor and the premium NVIDIA GeForce RTX 4080 graphics.', 76990000, 3, N'Asus', N'https://vn.store.asus.com/media/catalog/product/cache/31a3e9bc5cea0340b4f268573c7bdbfd/0/3/03_real_scar_g733_17_l.jpg', 1, N'Thong Minh', CAST(N'2024-05-20' AS Date), N'Thong Minh', CAST(N'2024-05-20' AS Date), 1, 2, N'https://m.media-amazon.com/images/I/6114EcYftqL._AC_SL1500_.jpg', N'https://m.media-amazon.com/images/I/61rt7KkUE3L._AC_SL1500_.jpg', N'https://m.media-amazon.com/images/I/61iyvk+75wL._AC_SL1500_.jpg')
INSERT [dbo].[Product] ([ProductId], [ProductName], [ProductDescription], [ProductPrice], [ProductQuantity], [ProductBrand], [ProductImage], [ProductStatus], [CreateBy], [CreateDate], [ModifyBy], [ModifyDate], [CategoryId], [AdminID], [Image1], [Image2], [Image3]) VALUES (4, N'Gaming Asus TUF M4 Wireless Mouse', N'The Asus TUF M4 Wireless Gaming Mouse is designed for gamers seeking reliability and performance. Featuring a sleek, ergonomic design, this mouse provides a comfortable grip for extended gaming sessions. Equipped with a high-precision optical sensor, it offers exceptional accuracy and responsiveness.', 890000, 3, N'Asus', N'https://product.hstatic.net/200000722513/product/tuf-gaming-m4-wireless-01_7417eaf359974d01a42eda3d0bbd4cb2_1024x1024.jpg', 1, N'Thong Minh', CAST(N'2024-05-20' AS Date), N'Thong Minh', CAST(N'2024-05-20' AS Date), 2, 2, N'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/_/0/_0003_64816_chuot_game_khong_day_asus.jpg', N'https://cdn2.cellphones.com.vn/x/media/catalog/product/_/0/_0000_64816_chuot_game_khong_day_asus_1_.jpg', N'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/_/0/_0001_64816_chuot_game_khong_day_asus_2_.jpg')
INSERT [dbo].[Product] ([ProductId], [ProductName], [ProductDescription], [ProductPrice], [ProductQuantity], [ProductBrand], [ProductImage], [ProductStatus], [CreateBy], [CreateDate], [ModifyBy], [ModifyDate], [CategoryId], [AdminID], [Image1], [Image2], [Image3]) VALUES (5, N'Gaming Asus TUF M3 Mouse', N'The Asus TUF M3 Gaming Mouse is a compact and lightweight mouse designed for gamers who demand both performance and durability. Featuring a high-precision optical sensor, it delivers accurate tracking and rapid responsiveness, essential for competitive gaming.', 440000, 3, N'Asus', N'https://product.hstatic.net/200000722513/product/n-chuot-may-tinh-asus-tuf-gaming-m3-1_1084923e02ee4a489f1e9982f7e8d087_5ac22a4ce159441584ae4049c77390df_1024x1024.jpg', 1, N'Thong Minh', CAST(N'2024-05-20' AS Date), N'Thong Minh', CAST(N'2024-05-20' AS Date), 2, 2, N'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/_/0/_0001_51521_tuf_gaming_m3_08.jpg', N'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/_/0/_0004_51521_tuf_gaming_m3_02.jpg', N'https://cdn2.cellphones.com.vn/x/media/catalog/product/_/0/_0003_51521_tuf_gaming_m3_01.jpg')
INSERT [dbo].[Product] ([ProductId], [ProductName], [ProductDescription], [ProductPrice], [ProductQuantity], [ProductBrand], [ProductImage], [ProductStatus], [CreateBy], [CreateDate], [ModifyBy], [ModifyDate], [CategoryId], [AdminID], [Image1], [Image2], [Image3]) VALUES (6, N'Asus Rog Gladius III Wireless AimPoint EVA-02 Edition Mouse', N'The Asus ROG Gladius III Wireless AimPoint EVA-02 Edition Mouse is a premium gaming mouse designed for enthusiasts and fans of the Evangelion series. Featuring the latest AimPoint sensor technology, it provides ultra-precise tracking with up to 36,000 DPI, ensuring exceptional accuracy and responsiveness for competitive gaming.', 3550000, 3, N'Asus', N'https://file.hstatic.net/200000722513/file/gearvn-chuot-asus-rog-rog-gladius-iii-wireless-aimpoint-eva-02-edition_26b468ac9008402abd6896aa54e724ce_grande.png', 1, N'Thong Minh', CAST(N'2024-05-20' AS Date), N'Thong Minh', CAST(N'2024-05-20' AS Date), 2, 2, N'https://m.media-amazon.com/images/I/81Mgr2dJ9xL._AC_SL1500_.jpg', N'https://m.media-amazon.com/images/I/81FB4ioejiL._AC_SL1500_.jpg', N'https://m.media-amazon.com/images/I/91i89sIOn0L._AC_SL1500_.jpg')
INSERT [dbo].[Product] ([ProductId], [ProductName], [ProductDescription], [ProductPrice], [ProductQuantity], [ProductBrand], [ProductImage], [ProductStatus], [CreateBy], [CreateDate], [ModifyBy], [ModifyDate], [CategoryId], [AdminID], [Image1], [Image2], [Image3]) VALUES (7, N'Keyboard Asus ROG Strix Scope NX TKL Red Switch', N'Asus ROG Strix Scope NX TKL is designed with a tenkeyless layout that eliminates the typical number key, this makes the keyboard more compact, frees up desk space, and provides a wider range of mouse movement.', 1990000, 3, N'Asus', N'https://hanoicomputercdn.com/media/product/62272_ban_phim_asus_rog_strix_scope_nx_tkl_red_sw_0003_4.jpg', 1, N'Thong Minh', CAST(N'2024-05-20' AS Date), N'Thong Minh', CAST(N'2024-05-20' AS Date), 3, 2, N'https://hanoicomputercdn.com/media/product/62272_ban_phim_asus_rog_strix_scope_nx_tkl_red_sw_0003_4.jpg', N'https://hanoicomputercdn.com/media/product/62272_ban_phim_asus_rog_strix_scope_nx_tkl_red_sw_0000_1.jpg', N'https://hanoicomputercdn.com/media/product/62272_ban_phim_asus_rog_strix_scope_nx_tkl_red_sw_0002_3.jpg')
INSERT [dbo].[Product] ([ProductId], [ProductName], [ProductDescription], [ProductPrice], [ProductQuantity], [ProductBrand], [ProductImage], [ProductStatus], [CreateBy], [CreateDate], [ModifyBy], [ModifyDate], [CategoryId], [AdminID], [Image1], [Image2], [Image3]) VALUES (8, N'Keyboard ASUS ROG Strix Flare II Nx Blue Switch', N'Not only outstanding in PC component product lines, ASUS is also known as one of the brands that specializes in bringing extremely high-class gaming gear peripherals in terms of design and performance, from headset lines. gaming, multi-connected computer mice from wired to wireless and even gaming keyboards.', 3590000, 3, N'Asus', N'https://product.hstatic.net/200000722513/product/phim_507f44fa3adc485db66c50cad6d78d3a_6ebc199f784349d3ac100d13c576a390_2b321b3f12ca4eb4a4f43ec39b49b988_grande.png', 1, N'Thong Minh', CAST(N'2024-05-20' AS Date), N'Thong Minh', CAST(N'2024-05-20' AS Date), 3, 2, N'https://hanoicomputercdn.com/media/product/65933_ban_phim_game_khong_day_asus_rog_strix_flare_ii_10.jpg', N'https://hanoicomputercdn.com/media/product/65933_ban_phim_game_khong_day_asus_rog_strix_flare_ii_3.jpg', N'https://hanoicomputercdn.com/media/product/65933_ban_phim_game_khong_day_asus_rog_strix_flare_ii_9.jpg')
INSERT [dbo].[Product] ([ProductId], [ProductName], [ProductDescription], [ProductPrice], [ProductQuantity], [ProductBrand], [ProductImage], [ProductStatus], [CreateBy], [CreateDate], [ModifyBy], [ModifyDate], [CategoryId], [AdminID], [Image1], [Image2], [Image3]) VALUES (9, N'Keyboard Asus TUF Gaming K3', N'ASUS TUF Gaming K3 RGB mechanical keyboard with N-key multitasking, combination multimedia keys, USB 2.0 expansion port, aluminum alloy top shell, wrist rest, eight macro-capable keys Aura Sync lighting and programming.', 1790000, 3, N'Asus', N'https://product.hstatic.net/200000722513/product/31de_f65bf9eebca74ac98b72a37efd27281c_4e82eb485d074ee3a3eb377a510af115_76fcf8e9e5c642b18800be85da66b67a_grande.jpg', 1, N'Thong Minh', CAST(N'2024-05-20' AS Date), N'Thong Minh', CAST(N'2024-05-20' AS Date), 3, 2, N'https://product.hstatic.net/200000722513/product/25f5d8789d826c05974252bd736cdbfd_798a8afa71f348e1b1ede2d3fc65907c_5490b19da0a84940a20773da8a75bc44_1024x1024.jpg', N'https://product.hstatic.net/200000722513/product/2_934bedf91ce646babb842d0dfdf98868_a6b01fb73ef8490fbee49b2d4208c233_1024x1024.png', N'https://product.hstatic.net/200000722513/product/3_b4923143527040ebab5dbd375354add9_62d7575fec0345209fbafe7434cde031_1024x1024.png')
INSERT [dbo].[Product] ([ProductId], [ProductName], [ProductDescription], [ProductPrice], [ProductQuantity], [ProductBrand], [ProductImage], [ProductStatus], [CreateBy], [CreateDate], [ModifyBy], [ModifyDate], [CategoryId], [AdminID], [Image1], [Image2], [Image3]) VALUES (10, N'Bluetooth Apple AirPods Pro 2 2023 USB-C', N'AirPods Pro 2023 is a true wireless headset product that has been significantly upgraded compared to the old version. Outstanding new features such as USB-C charging port, IPX4 water resistance, improved sound quality and powerful noise cancellation will bring a better experience to users.', 5600000, 3, N'Apple', N'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/a/p/apple-airpods-pro-2-usb-c_8_.png', 1, N'Thong Minh', CAST(N'2024-05-20' AS Date), N'Thong Minh', CAST(N'2024-05-20' AS Date), 4, 2, N'https://cdn-v2.didongviet.vn/files/products/2023/10/8/1/1699438664909_1_airpods_pro_2_didongviet.jpg', N'https://cdn-v2.didongviet.vn/files/products/2023/10/8/1/1699438666952_2_airpods_pro_2_didongviet.jpg', N'https://cdn-v2.didongviet.vn/files/products/2023/10/8/1/1699438673628_5_airpods_pro_2_didongviet.jpg')
INSERT [dbo].[Product] ([ProductId], [ProductName], [ProductDescription], [ProductPrice], [ProductQuantity], [ProductBrand], [ProductImage], [ProductStatus], [CreateBy], [CreateDate], [ModifyBy], [ModifyDate], [CategoryId], [AdminID], [Image1], [Image2], [Image3]) VALUES (11, N'Apple EarPods Lightning (MMTN2)', N'The Lightning Apple Earpods MMTN2 headset is an indispensable product if you are an Apple fan. Apple Earpods Lightning is an excellent product suitable for iPhone, iPad, and iPod phones that support iOS 10 and above. Come to CellphoneS to own a highly convenient Earpods Lightning MMTN2 headset and the best low-cost audio devices.', 500000, 3, N'Apple', N'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/f/r/frame_3_3.png', 1, N'Thong Minh', CAST(N'2024-05-20' AS Date), N'Thong Minh', CAST(N'2024-05-20' AS Date), 4, 2, N'https://cdn-v2.didongviet.vn/files/media/catalog/product/t/a/tai-nghe-apple-earpods-lightning-didongviet-3.jpg', N'https://cdn-v2.didongviet.vn/files/media/catalog/product/t/a/tai-nghe-apple-earpods-lightning-didongviet-1.jpg', N'https://cdn-v2.didongviet.vn/files/media/catalog/product/t/a/tai-nghe-apple-earpods-lightning-didongviet-2.jpg')
INSERT [dbo].[Product] ([ProductId], [ProductName], [ProductDescription], [ProductPrice], [ProductQuantity], [ProductBrand], [ProductImage], [ProductStatus], [CreateBy], [CreateDate], [ModifyBy], [ModifyDate], [CategoryId], [AdminID], [Image1], [Image2], [Image3]) VALUES (12, N'Bluetooth Apple AirPods 2', N'Apple Airpods 2 has a dedicated H1 chip that helps quickly transfer calls from iPhone to Airpods as well as extremely low power consumption. Usage time is up to 5 hours of music or 3 hours of talk time and when combined with the charging box for up to 24 hours.', 2690000, 3, N'Apple', N'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_169_2.png', 1, N'Thong Minh', CAST(N'2024-05-20' AS Date), N'Thong Minh', CAST(N'2024-05-20' AS Date), 4, 2, N'https://cdn2.cellphones.com.vn/x/media/catalog/product/i/m/image_2019-03-29_09-12-07_2.jpg', N'https://cdn2.cellphones.com.vn/x/media/catalog/product/a/z/azaudio-tai-nghe-bluetooth-apple-airpod-2_2.jpg', N'https://cdn2.cellphones.com.vn/x/media/catalog/product/m/a/maxresdefault_1_2.jpg')
SET IDENTITY_INSERT Product OFF;


INSERT INTO Specification (Color, Size, Weight, Manufacturer, ProductName, ProductId)
VALUES 
('Blue', '14.0', '1.47', 'Asus', 'Laptop Asus ExpertBook B1400CEAE BV3186W', 1),
('Red', '14.0', '1.2', 'Asus', 'Laptop Asus VivoBook 14 OLED M1405YA KM047W',2),
('Black', '17.0', '3', 'Asus', 'ASUS ROG Strix SCAR 17 G733PZ LL980W',3),
('Black White', '12.0', '0.45', 'DELL', 'DELL Gaming DELL TUF M4 Wireless Mice', 4),
('Black White', '9.0', '0.95', 'DELL', 'DELL Gaming DELL TUF M3 Mice', 5),
('Black White', '11.5', '1.21', 'DELL', 'DELL Rog Gladius III Wireless AimPoint EVA-02 Edition Mice', 6),
('White', '5.3', '2.45', 'Apple', 'Bluetooth Apple AirPods Pro 2 2023 USB-C', 7),
('White', '2.1', '1.2', 'Apple', 'Apple EarPods Lightning (MMTN2)', 8),
('White', '4', '1.21', 'Apple', 'Bluetooth Apple AirPods 2', 9),
('Black', '39.7', '160.0', 'Asus', 'Asus ROG Strix Scope NX TKL Red Switch', 10 ),
('Black', '38.0', '200.0', 'Asus', 'ASUS ROG Strix Flare II Nx Blue Switch', 11 ),
('Black', '39.7', '190.0', 'Asus', 'Asus TUF Gaming K3', 12 );


INSERT INTO ProductImage ( ProductId, [Image])
VALUES	( 1, 'https://product.hstatic.net/200000420363/product/laptop-asus-expertbook-b1400ceae-bv3186w_14a86dbdbecd4dc99d3c860e82827cc1_master.png'),
		( 1, 'https://product.hstatic.net/200000722513/product/thumb_b1_25831d5fc18e45b1ada9ed3f184c9815_grande.png'),
		( 2, 'https://vn.store.asus.com/media/catalog/product/cache/31a3e9bc5cea0340b4f268573c7bdbfd/m/1/m1405ya-km047w-1.png'),
		( 2, 'https://vn.store.asus.com/media/catalog/product/cache/31a3e9bc5cea0340b4f268573c7bdbfd/m/i/mil-std_810h_1.png'),
		( 3, 'https://vn.store.asus.com/media/catalog/product/cache/31a3e9bc5cea0340b4f268573c7bdbfd/0/3/03_real_scar_g733_17_l.jpg'),
		( 3, 'https://vn.store.asus.com/media/catalog/product/cache/31a3e9bc5cea0340b4f268573c7bdbfd/0/4/04_real_scar_g733_17_l.jpg'),
		( 4, 'https://product.hstatic.net/200000722513/product/tuf-gaming-m4-wireless-02_56fe3b15890748738508eb07f20c43c5_1024x1024.jpg'),
		( 4, 'https://product.hstatic.net/200000722513/product/tuf-gaming-m4-wireless-04_246e3e5ac7e54112b7d7ebe698f0fd1d_1024x1024.jpg'),
		( 5, 'https://product.hstatic.net/200000722513/product/n-chuot-may-tinh-asus-tuf-gaming-m3-1_1084923e02ee4a489f1e9982f7e8d087_5ac22a4ce159441584ae4049c77390df_1024x1024.jpg'),
		( 5, 'https://product.hstatic.net/200000722513/product/n-chuot-may-tinh-asus-tuf-gaming-m3-7_697d2cedaa2e4a8a9bbf5868a0bd38af_f0acf0628aa24e6a97ce47fb77fce3df_1024x1024.jpg'),
		( 6, 'https://file.hstatic.net/200000722513/file/gearvn-chuot-asus-rog-rog-gladius-iii-wireless-aimpoint-eva-02-edition_26b468ac9008402abd6896aa54e724ce_grande.png'),
		( 6, 'https://www.tncstore.vn/media/product/9195-chuot-eva-2.jpg'),
		( 7, 'https://hanoicomputercdn.com/media/product/62272_ban_phim_asus_rog_strix_scope_nx_tkl_red_sw_0003_4.jpg'),
		( 7, 'https://hanoicomputercdn.com/media/product/62272_ban_phim_asus_rog_strix_scope_nx_tkl_red_sw_0000_1.jpg'),
		( 8, 'https://product.hstatic.net/200000722513/product/phim_507f44fa3adc485db66c50cad6d78d3a_6ebc199f784349d3ac100d13c576a390_2b321b3f12ca4eb4a4f43ec39b49b988_grande.png'),
		( 8, 'https://product.hstatic.net/200000722513/product/31de_f65bf9eebca74ac98b72a37efd27281c_4e82eb485d074ee3a3eb377a510af115_76fcf8e9e5c642b18800be85da66b67a_grande.jpg'),
		( 9, 'https://product.hstatic.net/200000722513/product/31de_f65bf9eebca74ac98b72a37efd27281c_4e82eb485d074ee3a3eb377a510af115_76fcf8e9e5c642b18800be85da66b67a_grande.jpg'),
		( 9, 'https://img.websosanh.vn/v2/users/root_product/images/ban-phim-keyboard-asus-tuf-gam/4kz72vxdrix9m.jpg'),
		( 10, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/a/p/apple-airpods-pro-2-usb-c_8_.png'),
		( 10, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/a/p/apple-airpods-pro-2-usb-c_7_.png'),
		( 11, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/f/r/frame_3_3.png'),
		( 11, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/a/u/audifonos-earpods-apple-iphone-7-8-x-lightning-original-d_nq_np_615134-mpe27006605233_032018-f.jpg'),
		( 12, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_169_2.png'),
		( 12, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/a/i/airpods2-s-1.png')

		INSERT [dbo].[CategoryBlog] ([CategoryBlogId], [CategoryBlogName], [CategoryBlogStatus]) VALUES (1, N'Laptop', 1)
		INSERT [dbo].[CategoryBlog] ([CategoryBlogId], [CategoryBlogName], [CategoryBlogStatus]) VALUES (2, N'Mouse', 1)
		INSERT [dbo].[CategoryBlog] ([CategoryBlogId], [CategoryBlogName], [CategoryBlogStatus]) VALUES (3, N'Keyboard', 1)
		INSERT [dbo].[CategoryBlog] ([CategoryBlogId], [CategoryBlogName], [CategoryBlogStatus]) VALUES (4, N'Headphone', 1)

		INSERT [dbo].[Blog] ([BlogTitle], [BlogUpdateDate], [BlogContent], [BlogThumbnail], [AdminId], [CategoryBlogId], [BlogStatus], [img], [url]) 
		VALUES (N'khuyen mai', CAST(N'2020-10-20' AS Date), N'xin chao', N'hello', 1, 1, 1, N'https://laptop88.vn/media/news/550_800x300_flash_sale.png', N'http://localhost:9999/MiniProject/category?categoryId=1#productList')

		INSERT INTO OrderHistory (Quantity, OrderDate, [Name], Phone, [Address], UserId, RoleId, TotalMoney, IsSuccess, OrderHistoryStatus, PaymentMethod)
		VALUES
		(3, '2024-06-07', 'Alice Johnson', 0987654321, 'Hanoi', 1, 2, 31370000, 'Pending', 1, 'QR'),
		(4, '2024-06-05', 'Alice Johnson', 0987654321, 'Hanoi', 1, 2, 9290000, 'Success', 1, 'ATM');

		INSERT INTO OrderHistoryDetail (SerialNumber, Price, OrderHistoryId, ProductId)
		VALUES
		('LA431424', 1000, 1, 1),
		('LA873457', 1000, 1, 1),
		('LA907036', 1000, 1, 2),
		('HE123125', 1000, 2, 10),
		('HE342612', 1000, 2, 11),
		('HE768745', 1000, 2, 11),
		('HE329812', 1000, 2, 12);

		INSERT INTO [Order] (OrderStatus, OrderHistoryId)
		VALUES
		(1, 1),
		(1 ,2);


		INSERT INTO OrderDetail (OrderId, OrderHistoryId, OrderHistoryDetailId)
		VALUES
		(1, 1, 1),
		(1, 1, 2),
		(1, 1, 3),
		(2, 2, 4),
		(2, 2, 5),
		(2, 2, 6),
		(2, 2, 7);



		
		INSERT INTO Warehouse (SerialNumber, ProductId)
		VALUES
		('LA431424', 1),
		('LA873457', 1),
		('LA907036', 2),
		('HE123125', 10),
		('HE342612', 11),
		('HE768745', 11),
		('HE329812', 12);
		
		



--Join Customer, Admin, Role for View Only
CREATE VIEW AdminCustomerRoleView AS
	SELECT 
    A.AdminID AS ID,
    'Admin' AS Type,
    A.AdminName AS Name,
    A.AdminAge AS Age,
    A.AdminEmail AS Email,
    A.AdminPassword AS Password,
    A.AdminGender AS Gender,
    A.AdminAddress AS Address,
    A.AdminCity AS City,
    A.AdminAvatar AS Avatar,
    A.AdminPhoneNum AS PhoneNumber,
    A.AdminDOB AS DOB,
    A.AdminStatus AS Status,
    R.RoleId,
    R.RoleName,
    R.RoleStatus
FROM Admin A
JOIN [Role] R ON A.RoleId = R.RoleId
UNION ALL
SELECT 
    C.CustomerID AS ID,
    'Customer' AS Type,
    C.CustomerName AS Name,
    C.CustomerAge AS Age,
    C.CustomerEmail AS Email,
    C.CustomerPassword AS Password,
    C.CustomerGender AS Gender,
    C.CustomerAddress AS Address,
    C.CustomerCity AS City,
    C.CustomerAvatar AS Avatar,
    C.CustomerPhoneNumber AS PhoneNumber,
    C.CustomerDOB AS DOB,
    C.CustomerStatus AS Status,
    R.RoleId,
    R.RoleName,
    R.RoleStatus
FROM Customer C
JOIN [Role] R ON C.RoleId = R.RoleId;


SELECT * FROM AdminCustomerRoleView;

-- Create the Feedback table
INSERT INTO Feedback (UserName, UserId, RoleId, StarRate, FeedbackDescription, FeedbackDate, FeedbackStatus, OrderId, ProductId)
VALUES
    ('Alice Johnson', 1, 2, 4, 'Great product, fast shipping!', '2024-06-01', 1, 1, 1),
    ('Alice Johnson', 1, 2, 5, 'Excellent service and quality!', '2024-06-02', 1, 1, 2),
    ('Alice Johnson', 1, 2, 3, 'Product was okay, could be better', '2024-06-03', 1, 1, 3),
    (2, 1, 2, 'Disappointed with the product quality', '2024-06-04', 1, 2, 4),
    (2, 1, 4, 'Good experience overall', '2024-06-05', 1, 2, 5);
