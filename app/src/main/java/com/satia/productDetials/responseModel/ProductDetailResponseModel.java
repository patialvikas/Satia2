package com.satia.productDetials.responseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ProductDetailResponseModel {
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

            @SerializedName("Type")
            @Expose
            private String type;

            @SerializedName("product_type")
            @Expose
            private String product_type;


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
            @SerializedName("No of Sheet")
            @Expose
            private String noOfSheet;
            @SerializedName("Ream Weight")
            @Expose
            private String reamWeight;
            @SerializedName("No of Ream Unit")
            @Expose
            private String noOfReamUnit;
            @SerializedName("No of Unit Pallets")
            @Expose
            private String noOfUnitPallets;
            @SerializedName("Pallet Weight")
            @Expose
            private String palletWeight;
            @SerializedName("Status")
            @Expose
            private String status;

            public String getType() {
                return type;
            }

            public String getProduct_type() {
                return product_type;
            }

            public void setProduct_type(String product_type) {
                this.product_type = product_type;
            }

            public void setType(String type) {
                this.type = type;
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

            public String getNoOfSheet() {
                return noOfSheet;
            }

            public void setNoOfSheet(String noOfSheet) {
                this.noOfSheet = noOfSheet;
            }

            public String getReamWeight() {
                return reamWeight;
            }

            public void setReamWeight(String reamWeight) {
                this.reamWeight = reamWeight;
            }

            public String getNoOfReamUnit() {
                return noOfReamUnit;
            }

            public void setNoOfReamUnit(String noOfReamUnit) {
                this.noOfReamUnit = noOfReamUnit;
            }

            public String getNoOfUnitPallets() {
                return noOfUnitPallets;
            }

            public void setNoOfUnitPallets(String noOfUnitPallets) {
                this.noOfUnitPallets = noOfUnitPallets;
            }

            public String getPalletWeight() {
                return palletWeight;
            }

            public void setPalletWeight(String palletWeight) {
                this.palletWeight = palletWeight;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

        }
    }
}
