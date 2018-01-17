package raven.data.entity;

/**
 * @author yi.liang
 * @since JDK1.8
 */
public interface VirtualDelete {

    /**
     * 是否虚拟删除
     *
     * @return
     */
    boolean isDel();


    /**
     * 是否虚拟删除
     *
     * @return
     */
    void setDel(boolean del);
}
