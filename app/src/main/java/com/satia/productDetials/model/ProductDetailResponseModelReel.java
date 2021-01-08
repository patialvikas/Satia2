package com.satia.productDetials.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;







public class ProductDetailResponseModelReel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }



    public class Data {

        @SerializedName("product_id")
        @Expose
        private Integer productId;
        @SerializedName("options")
        @Expose
        private Options options;

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public Options getOptions() {
            return options;
        }

        public void setOptions(Options options) {
            this.options = options;
        }

        public class Options {

            @SerializedName("No of Sheet")
            @Expose
            private Object noOfSheet;


            @SerializedName("Ream Weight")
            @Expose
            private Object reamWeight;
            @SerializedName("No of Ream Unit")
            @Expose
            private Object noOfReamUnit;
            @SerializedName("No of Unit Pallets")
            @Expose
            private Object noOfUnitPallets;
            @SerializedName("Pallet Weight")
            @Expose
            private Object palletWeight;
            @SerializedName("Status")
            @Expose
            private Object status;
            @SerializedName("User Name")
            @Expose
            private String userName;
            @SerializedName("product_type")
            @Expose
            private String product_type;

            @SerializedName("Machine")
            @Expose
            private String machine;
            @SerializedName("Product")
            @Expose
            private String product;
            @SerializedName("Quality")
            @Expose
            private String quality;
            @SerializedName("Lot Number")
            @Expose
            private String lotNumber;
            @SerializedName("GSM")
            @Expose
            private String gSM;
            @SerializedName("Size")
            @Expose
            private String size;
            @SerializedName("Reel Number")
            @Expose
            private String reelNumber;
            @SerializedName("Net Weight")
            @Expose
            private String netWeight;
            @SerializedName("Date of manufacture")
            @Expose
            private String dateOfManufacture;

            public Object getNoOfSheet() {
                return noOfSheet;
            }

            public void setProduct_type(String product_type) {
                this.product_type = product_type;
            }

            public String getProduct_type() {
                return product_type;
            }

            public void setNoOfSheet(Object noOfSheet) {
                this.noOfSheet = noOfSheet;
            }

            public Object getReamWeight() {
                return reamWeight;
            }

            public void setReamWeight(Object reamWeight) {
                this.reamWeight = reamWeight;
            }

            public Object getNoOfReamUnit() {
                return noOfReamUnit;
            }

            public void setNoOfReamUnit(Object noOfReamUnit) {
                this.noOfReamUnit = noOfReamUnit;
            }

            public Object getNoOfUnitPallets() {
                return noOfUnitPallets;
            }

            public void setNoOfUnitPallets(Object noOfUnitPallets) {
                this.noOfUnitPallets = noOfUnitPallets;
            }

            public Object getPalletWeight() {
                return palletWeight;
            }

            public void setPalletWeight(Object palletWeight) {
                this.palletWeight = palletWeight;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getMachine() {
                return machine;
            }

            public void setMachine(String machine) {
                this.machine = machine;
            }

            public String getProduct() {
                return product;
            }

            public void setProduct(String product) {
                this.product = product;
            }

            public String getQuality() {
                return quality;
            }

            public void setQuality(String quality) {
                this.quality = quality;
            }

            public String getLotNumber() {
                return lotNumber;
            }

            public void setLotNumber(String lotNumber) {
                this.lotNumber = lotNumber;
            }

            public String getGSM() {
                return gSM;
            }

            public void setGSM(String gSM) {
                this.gSM = gSM;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getReelNumber() {
                return reelNumber;
            }

            public void setReelNumber(String reelNumber) {
                this.reelNumber = reelNumber;
            }

            public String getNetWeight() {
                return netWeight;
            }

            public void setNetWeight(String netWeight) {
                this.netWeight = netWeight;
            }

            public String getDateOfManufacture() {
                return dateOfManufacture;
            }

            public void setDateOfManufacture(String dateOfManufacture) {
                this.dateOfManufacture = dateOfManufacture;
            }

        }

    }

}
