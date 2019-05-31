USE [master]
GO
/****** Object:  Database [FoodSystem]    Script Date: 6/1/2019 1:57:32 AM ******/
CREATE DATABASE [FoodSystem]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'FoodSystem', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXP12\MSSQL\DATA\FoodSystem.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'FoodSystem_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXP12\MSSQL\DATA\FoodSystem_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [FoodSystem] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [FoodSystem].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [FoodSystem] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [FoodSystem] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [FoodSystem] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [FoodSystem] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [FoodSystem] SET ARITHABORT OFF 
GO
ALTER DATABASE [FoodSystem] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [FoodSystem] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [FoodSystem] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [FoodSystem] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [FoodSystem] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [FoodSystem] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [FoodSystem] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [FoodSystem] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [FoodSystem] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [FoodSystem] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [FoodSystem] SET  DISABLE_BROKER 
GO
ALTER DATABASE [FoodSystem] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [FoodSystem] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [FoodSystem] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [FoodSystem] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [FoodSystem] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [FoodSystem] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [FoodSystem] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [FoodSystem] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [FoodSystem] SET  MULTI_USER 
GO
ALTER DATABASE [FoodSystem] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [FoodSystem] SET DB_CHAINING OFF 
GO
ALTER DATABASE [FoodSystem] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [FoodSystem] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [FoodSystem]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 6/1/2019 1:57:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[UserCode] [nvarchar](50) NOT NULL,
	[UserPassword] [nvarchar](50) NULL,
	[UserName] [nvarchar](50) NULL,
	[UserAddress] [nvarchar](50) NULL,
	[UserPhoneNo] [int] NULL,
	[UserEmail] [nvarchar](50) NULL,
	[UserStatus] [int] NULL,
	[RoleId] [int] NULL,
 CONSTRAINT [PK_Customer_1] PRIMARY KEY CLUSTERED 
(
	[UserCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BankAccount]    Script Date: 6/1/2019 1:57:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BankAccount](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [nvarchar](50) NOT NULL,
	[AccName] [nvarchar](50) NULL,
	[AccCardNo] [int] NULL,
	[AccMoney] [float] NULL,
 CONSTRAINT [PK_Account_1] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Category]    Script Date: 6/1/2019 1:57:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryId] [int] NOT NULL,
	[CategoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[CategoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DetailOrder]    Script Date: 6/1/2019 1:57:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetailOrder](
	[OrderId] [int] NOT NULL,
	[ProductId] [nvarchar](50) NOT NULL,
	[Quantity] [int] NULL,
	[Price] [float] NULL,
 CONSTRAINT [PK_DetailOrder] PRIMARY KEY CLUSTERED 
(
	[OrderId] ASC,
	[ProductId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Order]    Script Date: 6/1/2019 1:57:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[CusId] [nvarchar](50) NULL,
	[OrderDate] [datetime] NULL,
	[Total] [float] NULL,
	[Notes] [nvarchar](500) NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Payment]    Script Date: 6/1/2019 1:57:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payment](
	[PayId] [int] IDENTITY(1,1) NOT NULL,
	[OrderId] [int] NULL,
	[PayStatus] [int] NULL,
 CONSTRAINT [PK_Payment] PRIMARY KEY CLUSTERED 
(
	[PayId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Product]    Script Date: 6/1/2019 1:57:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ProCode] [nvarchar](50) NOT NULL,
	[StoreId] [nvarchar](50) NULL,
	[ProName] [nvarchar](50) NULL,
	[ProPrice] [float] NULL,
	[ProImage] [nvarchar](50) NULL,
	[ProQuantity] [int] NULL,
	[ProDescription] [nvarchar](50) NULL,
	[ProStatus] [int] NULL,
	[CategoryId] [int] NULL,
 CONSTRAINT [PK_Product_1] PRIMARY KEY CLUSTERED 
(
	[ProCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 6/1/2019 1:57:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[RoleId] [int] NOT NULL,
	[RoleName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[RoleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Store]    Script Date: 6/1/2019 1:57:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Store](
	[StoreCode] [nvarchar](50) NOT NULL,
	[StoreName] [nvarchar](50) NULL,
	[StoreAddress] [nvarchar](50) NULL,
	[StoreImage] [nchar](10) NULL,
	[StorePhoneNo] [int] NULL,
	[StoreStatus] [int] NULL,
 CONSTRAINT [PK_Store_1] PRIMARY KEY CLUSTERED 
(
	[StoreCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Transaction]    Script Date: 6/1/2019 1:57:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transaction](
	[AccountId] [int] NOT NULL,
	[PaymentId] [int] NOT NULL,
	[Amount] [float] NULL,
	[DateTrans] [datetime] NULL,
 CONSTRAINT [PK_Transaction] PRIMARY KEY CLUSTERED 
(
	[AccountId] ASC,
	[PaymentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Account] ([UserCode], [UserPassword], [UserName], [UserAddress], [UserPhoneNo], [UserEmail], [UserStatus], [RoleId]) VALUES (N'admin', N'123', N'HAHAHA', N'Quan 12, TP Ho Chi minh', 988272182, N'haipduy@gmail.com', 1, 1)
INSERT [dbo].[Account] ([UserCode], [UserPassword], [UserName], [UserAddress], [UserPhoneNo], [UserEmail], [UserStatus], [RoleId]) VALUES (N'user1', N'123', N'HiHI', N'Quan 1', 92832128, N'acv@gmail.com', 0, 2)
INSERT [dbo].[Account] ([UserCode], [UserPassword], [UserName], [UserAddress], [UserPhoneNo], [UserEmail], [UserStatus], [RoleId]) VALUES (N'user2', N'123', N'Tao la hai', N'Quan 12, TP Ho Chi minh', 987653425, N'haipduy@gmail', 0, 1)
INSERT [dbo].[Account] ([UserCode], [UserPassword], [UserName], [UserAddress], [UserPhoneNo], [UserEmail], [UserStatus], [RoleId]) VALUES (N'user3', N'123', N'HAHAHA', N'Quan 12, TP Ho Chi minh', 988272182, N'haipduy@gmail.com', 1, 1)
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (1, N'Drink')
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (2, N'Food')
INSERT [dbo].[Product] ([ProCode], [StoreId], [ProName], [ProPrice], [ProImage], [ProQuantity], [ProDescription], [ProStatus], [CategoryId]) VALUES (N'P001', N'St01', N'Redbull', 15, N'hihi', 100, N'bo huc', 1, 1)
INSERT [dbo].[Role] ([RoleId], [RoleName]) VALUES (1, N'Admin')
INSERT [dbo].[Role] ([RoleId], [RoleName]) VALUES (2, N'Customer')
INSERT [dbo].[Store] ([StoreCode], [StoreName], [StoreAddress], [StoreImage], [StorePhoneNo], [StoreStatus]) VALUES (N'St01', N'Banh Trang', N'123 Hoan kien', NULL, NULL, 1)
INSERT [dbo].[Store] ([StoreCode], [StoreName], [StoreAddress], [StoreImage], [StorePhoneNo], [StoreStatus]) VALUES (N'St02', N'Com hon tien', N'quan 1', N'hihi      ', -9883727, 1)
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([RoleId])
REFERENCES [dbo].[Role] ([RoleId])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
ALTER TABLE [dbo].[BankAccount]  WITH CHECK ADD  CONSTRAINT [FK_BankAccount_Account] FOREIGN KEY([UserId])
REFERENCES [dbo].[Account] ([UserCode])
GO
ALTER TABLE [dbo].[BankAccount] CHECK CONSTRAINT [FK_BankAccount_Account]
GO
ALTER TABLE [dbo].[BankAccount]  WITH CHECK ADD  CONSTRAINT [FK_BankAccount_Store] FOREIGN KEY([UserId])
REFERENCES [dbo].[Store] ([StoreCode])
GO
ALTER TABLE [dbo].[BankAccount] CHECK CONSTRAINT [FK_BankAccount_Store]
GO
ALTER TABLE [dbo].[DetailOrder]  WITH CHECK ADD  CONSTRAINT [FK_DetailOrder_Order] FOREIGN KEY([OrderId])
REFERENCES [dbo].[Order] ([Id])
GO
ALTER TABLE [dbo].[DetailOrder] CHECK CONSTRAINT [FK_DetailOrder_Order]
GO
ALTER TABLE [dbo].[DetailOrder]  WITH CHECK ADD  CONSTRAINT [FK_DetailOrder_Product] FOREIGN KEY([ProductId])
REFERENCES [dbo].[Product] ([ProCode])
GO
ALTER TABLE [dbo].[DetailOrder] CHECK CONSTRAINT [FK_DetailOrder_Product]
GO
ALTER TABLE [dbo].[Payment]  WITH CHECK ADD  CONSTRAINT [FK_Payment_Order] FOREIGN KEY([OrderId])
REFERENCES [dbo].[Order] ([Id])
GO
ALTER TABLE [dbo].[Payment] CHECK CONSTRAINT [FK_Payment_Order]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Category] ([CategoryId])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Store] FOREIGN KEY([StoreId])
REFERENCES [dbo].[Store] ([StoreCode])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Store]
GO
ALTER TABLE [dbo].[Transaction]  WITH CHECK ADD  CONSTRAINT [FK_Transaction_Account] FOREIGN KEY([AccountId])
REFERENCES [dbo].[BankAccount] ([Id])
GO
ALTER TABLE [dbo].[Transaction] CHECK CONSTRAINT [FK_Transaction_Account]
GO
ALTER TABLE [dbo].[Transaction]  WITH CHECK ADD  CONSTRAINT [FK_Transaction_Payment] FOREIGN KEY([PaymentId])
REFERENCES [dbo].[Payment] ([PayId])
GO
ALTER TABLE [dbo].[Transaction] CHECK CONSTRAINT [FK_Transaction_Payment]
GO
USE [master]
GO
ALTER DATABASE [FoodSystem] SET  READ_WRITE 
GO
