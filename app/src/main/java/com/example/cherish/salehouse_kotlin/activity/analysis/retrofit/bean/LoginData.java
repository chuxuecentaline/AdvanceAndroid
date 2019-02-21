package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean;

/**
 * @Author: cherish
 * @CreateDate: 2019/2/1 8:52
 */

public class LoginData {
    private String Token;
    private String NeedUploadCard;
    private EmployData EmployeeData;

    @Override
    public String toString() {
        return "LoginData{" + "Token='" + Token + '\'' + ", NeedUploadCard='" + NeedUploadCard +
                '\'' + ", EmployeeData=" + EmployeeData + '}';
    }

    class EmployData {
        private String EmpId;
        private String EmpName;
        private String EmpNo;
        private String DeptId;
        private String EMail;
        private String Mobile;
        private RegDateBean RegDate;
        private ModDateBean ModDate;
        private String RoleId;
        private int UserType;
        private Object CompanyName;
        private Object CheckReason;
        private Object WeiXinUnionid;
        private WeiXinBindingDateBean WeiXinBindingDate;
        private Object WeiXinNickname;
        private int AuthStatus;
        private Object IsShopOwner;
        private Object IsDisable;
        private int IsManagers;
        private String StoreName;
        private String ComName;
        private String Hroc_Code;
        private Object RoleName;
        private Object RoleDescription;
        private String Path;
        private int FlagType;
        private int DepartmentStatus;
        private int FlagBelong;
        private String CityName;
        private String DeptCompanyPath;
        private String FilePath;
        private String FileHeadUrl;
        private Object OtherImages;
        private String FullDepartMentName;
        private int EmpStatus;
        private int CardUpLoad;
        private String WeixinPicUrl;
        private String WxFilePath;
        private String NameAndPhone;

        public String getEmpId() {
            return EmpId;
        }

        public void setEmpId(String EmpId) {
            this.EmpId = EmpId;
        }

        public String getEmpName() {
            return EmpName;
        }

        public void setEmpName(String EmpName) {
            this.EmpName = EmpName;
        }

        public String getEmpNo() {
            return EmpNo;
        }

        public void setEmpNo(String EmpNo) {
            this.EmpNo = EmpNo;
        }

        public String getDeptId() {
            return DeptId;
        }

        public void setDeptId(String DeptId) {
            this.DeptId = DeptId;
        }

        public String getEMail() {
            return EMail;
        }

        public void setEMail(String EMail) {
            this.EMail = EMail;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public RegDateBean getRegDate() {
            return RegDate;
        }

        public void setRegDate(RegDateBean RegDate) {
            this.RegDate = RegDate;
        }

        public ModDateBean getModDate() {
            return ModDate;
        }

        public void setModDate(ModDateBean ModDate) {
            this.ModDate = ModDate;
        }

        public String getRoleId() {
            return RoleId;
        }

        public void setRoleId(String RoleId) {
            this.RoleId = RoleId;
        }

        public int getUserType() {
            return UserType;
        }

        public void setUserType(int UserType) {
            this.UserType = UserType;
        }

        public Object getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(Object CompanyName) {
            this.CompanyName = CompanyName;
        }

        public Object getCheckReason() {
            return CheckReason;
        }

        public void setCheckReason(Object CheckReason) {
            this.CheckReason = CheckReason;
        }

        public Object getWeiXinUnionid() {
            return WeiXinUnionid;
        }

        public void setWeiXinUnionid(Object WeiXinUnionid) {
            this.WeiXinUnionid = WeiXinUnionid;
        }

        public WeiXinBindingDateBean getWeiXinBindingDate() {
            return WeiXinBindingDate;
        }

        public void setWeiXinBindingDate(WeiXinBindingDateBean WeiXinBindingDate) {
            this.WeiXinBindingDate = WeiXinBindingDate;
        }

        public Object getWeiXinNickname() {
            return WeiXinNickname;
        }

        public void setWeiXinNickname(Object WeiXinNickname) {
            this.WeiXinNickname = WeiXinNickname;
        }

        public int getAuthStatus() {
            return AuthStatus;
        }

        public void setAuthStatus(int AuthStatus) {
            this.AuthStatus = AuthStatus;
        }

        public Object getIsShopOwner() {
            return IsShopOwner;
        }

        public void setIsShopOwner(Object IsShopOwner) {
            this.IsShopOwner = IsShopOwner;
        }

        public Object getIsDisable() {
            return IsDisable;
        }

        public void setIsDisable(Object IsDisable) {
            this.IsDisable = IsDisable;
        }

        public int getIsManagers() {
            return IsManagers;
        }

        public void setIsManagers(int IsManagers) {
            this.IsManagers = IsManagers;
        }

        public String getStoreName() {
            return StoreName;
        }

        public void setStoreName(String StoreName) {
            this.StoreName = StoreName;
        }

        public String getComName() {
            return ComName;
        }

        public void setComName(String ComName) {
            this.ComName = ComName;
        }

        public String getHroc_Code() {
            return Hroc_Code;
        }

        public void setHroc_Code(String Hroc_Code) {
            this.Hroc_Code = Hroc_Code;
        }

        public Object getRoleName() {
            return RoleName;
        }

        public void setRoleName(Object RoleName) {
            this.RoleName = RoleName;
        }

        public Object getRoleDescription() {
            return RoleDescription;
        }

        public void setRoleDescription(Object RoleDescription) {
            this.RoleDescription = RoleDescription;
        }

        public String getPath() {
            return Path;
        }

        public void setPath(String Path) {
            this.Path = Path;
        }

        public int getFlagType() {
            return FlagType;
        }

        public void setFlagType(int FlagType) {
            this.FlagType = FlagType;
        }

        public int getDepartmentStatus() {
            return DepartmentStatus;
        }

        public void setDepartmentStatus(int DepartmentStatus) {
            this.DepartmentStatus = DepartmentStatus;
        }

        public int getFlagBelong() {
            return FlagBelong;
        }

        public void setFlagBelong(int FlagBelong) {
            this.FlagBelong = FlagBelong;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        public String getDeptCompanyPath() {
            return DeptCompanyPath;
        }

        public void setDeptCompanyPath(String DeptCompanyPath) {
            this.DeptCompanyPath = DeptCompanyPath;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public String getFileHeadUrl() {
            return FileHeadUrl;
        }

        public void setFileHeadUrl(String FileHeadUrl) {
            this.FileHeadUrl = FileHeadUrl;
        }

        public Object getOtherImages() {
            return OtherImages;
        }

        public void setOtherImages(Object OtherImages) {
            this.OtherImages = OtherImages;
        }

        public String getFullDepartMentName() {
            return FullDepartMentName;
        }

        public void setFullDepartMentName(String FullDepartMentName) {
            this.FullDepartMentName = FullDepartMentName;
        }

        public int getEmpStatus() {
            return EmpStatus;
        }

        public void setEmpStatus(int EmpStatus) {
            this.EmpStatus = EmpStatus;
        }

        public int getCardUpLoad() {
            return CardUpLoad;
        }

        public void setCardUpLoad(int CardUpLoad) {
            this.CardUpLoad = CardUpLoad;
        }

        public String getWeixinPicUrl() {
            return WeixinPicUrl;
        }

        public void setWeixinPicUrl(String WeixinPicUrl) {
            this.WeixinPicUrl = WeixinPicUrl;
        }

        public String getWxFilePath() {
            return WxFilePath;
        }

        public void setWxFilePath(String WxFilePath) {
            this.WxFilePath = WxFilePath;
        }

        public String getNameAndPhone() {
            return NameAndPhone;
        }

        public void setNameAndPhone(String NameAndPhone) {
            this.NameAndPhone = NameAndPhone;
        }

        @Override
        public String toString() {
            return "EmployData{" + "EmpId='" + EmpId + '\'' + ", EmpName='" + EmpName + '\'' + "," +
                    " EmpNo='" + EmpNo + '\'' + ", DeptId='" + DeptId + '\'' + ", EMail='" +
                    EMail + '\'' + ", Mobile='" + Mobile + '\'' + ", RegDate=" + RegDate + ", " +
                    "ModDate=" + ModDate + ", RoleId='" + RoleId + '\'' + ", UserType=" +
                    UserType + ", CompanyName=" + CompanyName + ", CheckReason=" + CheckReason +
                    ", WeiXinUnionid=" + WeiXinUnionid + ", WeiXinBindingDate=" +
                    WeiXinBindingDate + ", WeiXinNickname=" + WeiXinNickname + ", AuthStatus=" +
                    AuthStatus + ", IsShopOwner=" + IsShopOwner + ", IsDisable=" + IsDisable + "," +
                    " IsManagers=" + IsManagers + ", StoreName='" + StoreName + '\'' + ", " +
                    "ComName='" + ComName + '\'' + ", Hroc_Code='" + Hroc_Code + '\'' + ", " +
                    "RoleName=" + RoleName + ", RoleDescription=" + RoleDescription + ", Path='"
                    + Path + '\'' + ", FlagType=" + FlagType + ", DepartmentStatus=" +
                    DepartmentStatus + ", FlagBelong=" + FlagBelong + ", CityName='" + CityName +
                    '\'' + ", DeptCompanyPath='" + DeptCompanyPath + '\'' + ", FilePath='" +
                    FilePath + '\'' + ", FileHeadUrl='" + FileHeadUrl + '\'' + ", OtherImages=" +
                    OtherImages + ", FullDepartMentName='" + FullDepartMentName + '\'' + ", " +
                    "EmpStatus=" + EmpStatus + ", CardUpLoad=" + CardUpLoad + ", WeixinPicUrl='"
                    + WeixinPicUrl + '\'' + ", WxFilePath='" + WxFilePath + '\'' + ", " +
                    "NameAndPhone='" + NameAndPhone + '\'' + '}';
        }

        class RegDateBean {
            /**
             * TimeStamp : 1462425487197
             * TimeText : 2016-05-05 13:18:07
             */

            private long TimeStamp;
            private String TimeText;

            public long getTimeStamp() {
                return TimeStamp;
            }

            public void setTimeStamp(long TimeStamp) {
                this.TimeStamp = TimeStamp;
            }

            public String getTimeText() {
                return TimeText;
            }

            public void setTimeText(String TimeText) {
                this.TimeText = TimeText;
            }

            @Override
            public String toString() {
                return "RegDateBean{" + "TimeStamp=" + TimeStamp + ", TimeText='" + TimeText + '\'' + '}';
            }
        }

        class ModDateBean {
            /**
             * TimeStamp : 1548659306067
             * TimeText : 2019-01-28 15:08:26
             */

            private long TimeStamp;
            private String TimeText;

            public long getTimeStamp() {
                return TimeStamp;
            }

            public void setTimeStamp(long TimeStamp) {
                this.TimeStamp = TimeStamp;
            }

            public String getTimeText() {
                return TimeText;
            }

            public void setTimeText(String TimeText) {
                this.TimeText = TimeText;
            }

            @Override
            public String toString() {
                return "ModDateBean{" + "TimeStamp=" + TimeStamp + ", TimeText='" + TimeText + '\'' + '}';
            }
        }

        class WeiXinBindingDateBean {
            /**
             * TimeStamp : 1534139617280
             * TimeText : 2018-08-13 13:53:37
             */

            private long TimeStamp;
            private String TimeText;

            public long getTimeStamp() {
                return TimeStamp;
            }

            public void setTimeStamp(long TimeStamp) {
                this.TimeStamp = TimeStamp;
            }

            public String getTimeText() {
                return TimeText;
            }

            public void setTimeText(String TimeText) {
                this.TimeText = TimeText;
            }

            @Override
            public String toString() {
                return "WeiXinBindingDateBean{" + "TimeStamp=" + TimeStamp + ", TimeText='" + TimeText + '\'' + '}';
            }
        }
    }

}
