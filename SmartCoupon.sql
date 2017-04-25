USE [SmartCoupon]
GO
/****** Object:  Table [dbo].[Company]    Script Date: 24/04/2017 06:44:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Company](
	[ID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[COMP_NAME] [varchar](50) NOT NULL,
	[PASSWORD] [varchar](50) NULL,
	[EMAIL] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Company_Coupon]    Script Date: 24/04/2017 06:44:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Company_Coupon](
	[COMP_ID] [numeric](18, 0) NOT NULL,
	[COUPON_ID] [numeric](18, 0) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[COMP_ID] ASC,
	[COUPON_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Coupon]    Script Date: 24/04/2017 06:44:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Coupon](
	[ID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[TITLE] [varchar](50) NULL,
	[START_DATE] [datetime] NULL,
	[END_DATE] [datetime] NULL,
	[AMOUNT] [int] NULL,
	[TYPE] [varchar](50) NULL,
	[MESSAGE] [varchar](50) NULL,
	[PRICE] [real] NULL,
	[IMAGE] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 24/04/2017 06:44:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Customer](
	[ID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[CUST_NAME] [varchar](50) NOT NULL,
	[PASSWORD] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Customer_Coupon]    Script Date: 24/04/2017 06:44:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer_Coupon](
	[CUST_ID] [numeric](18, 0) NOT NULL,
	[COUPON_ID] [numeric](18, 0) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CUST_ID] ASC,
	[COUPON_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
