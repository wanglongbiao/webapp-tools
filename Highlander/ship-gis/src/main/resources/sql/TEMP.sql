USE [test1]
GO

/****** Object:  Table [dbo].[OptDeviceInfo]    Script Date: 10/16/2017 17:17:11 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[OptDeviceInfo] (
  [ID]                 [INT]          NOT NULL,
  [szDesc]             [VARCHAR](128) NULL,
  [szDesc]             [VARCHAR](32)  NULL,
  [szDevName]          [VARCHAR](32)  NULL,
  [szClassName]        [CHAR]         NULL,
  [nDevId]             [INT]          NULL,
  [nOperType]          [INT]          NULL,
  [nDevType]           [INT]          NULL,
  [nWorkMode]          [INT]          NULL,
  [nState]             [INT]          NULL,
  [nMaxCtrlUserCount]  [INT]          NULL,
  [nMaxVideoLinkCount] [INT]          NULL,
  [nMaxWaittingCount]  [INT]          NULL,
  [nLong]              [INT]          NULL,
  [nLati]              [INT]          NULL,
  [nHeight]            [INT]          NULL,
  [fV_fix]             [FLOAT]        NULL,
  [fH_fix]             [FLOAT]        NULL,
  [nTVCtrlPort]        [INT]          NULL,
  [nPFCtrlPort]        [INT]          NULL,
  [nDevAttr1]          [INT]          NULL,
  [nDevAttr2]          [INT]          NULL,
  [nBeginAngle]        [INT]          NULL,
  [nEndAngle]          [INT]          NULL,
  [nReserved1]         [INT]          NULL,
  [nReserved2]         [INT]          NULL,
  [nElseAddrCount]     [INT]          NULL,
  CONSTRAINT [PK_OptDeviceInfo] PRIMARY KEY CLUSTERED
    (
      [ID] ASC
    )
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

