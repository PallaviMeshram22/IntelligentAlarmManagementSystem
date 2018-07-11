package com.siemens.scifive.intelligentalarmmanagementsystem.interfaces;

public interface GenericWSCallback {
    public void onPreExecuteIon();
    public void onIonComplete();
    public void onNetworkError(Exception e);
    public void onDataError(Exception e);
    public void onSuccess(Object o);
}
