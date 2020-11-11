package com.example.demotest.jvm;

public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK=null;
    public void isAlive(){
        System.out.println("Object is alive");
    }
    //重写finalize()方法，第一次标记时使虚拟机判定该对象需要执行finalize()方法
    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
        System.out.println("finalize() excute");
        FinalizeEscapeGC.SAVE_HOOK=this;//此行代码对该对象进行的拯救
    }
    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK=new FinalizeEscapeGC();

        //初次拯救：成功
        SAVE_HOOK=null;
        System.gc();
        Thread.sleep(1000);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("Object is dead");
        }

        //第二次拯救：失败
        SAVE_HOOK=null;
        System.gc();
        Thread.sleep(1000);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("Object is dead");
        }
    }
}

