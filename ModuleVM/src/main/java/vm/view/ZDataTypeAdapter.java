package vm.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ZDataTypeAdapter<T> extends BaseAdapter{

    private ViewHolderFactory mFactory;
    private LayoutInflater mInflater;

    private List<Object> allDatas = new ArrayList<>();

    private Map<Class,Integer> viewTypeMap = new HashMap<>();
    private int viewTypeCount = 1;

    public ZDataTypeAdapter(ViewHolderFactory viewHolderFactory){
        this.mFactory = viewHolderFactory;
    }

    public final boolean add(Object... datas){

        boolean needClean = allDatas.size() > 0;

        allDatas.clear();
        viewTypeMap.clear();

        boolean changed =  fillDatas(datas) || needClean ;

        if (changed) notifyDataSetChanged();

        return changed;
    }

    public final boolean append(Object... datas){

        boolean changed = fillDatas(datas);

        if (changed) {
            notifyDataSetChanged();
        }

        return true;
    }

    private boolean fillDatas(Object... datas){

        if (datas == null || datas.length == 0){
            return false;
        }

        for (Object data: datas) {
            if (!viewTypeMap.containsKey(data.getClass())) {
                viewTypeMap.put(data.getClass(), viewTypeCount - 1);
            }
            allDatas.add(data);
        }
        viewTypeCount = viewTypeMap.keySet().size();

        return true;
    }

    public final List<Object> getAllDatas() {

        return allDatas;
    }

    @Override
    public final long getItemId(int position) {

        return position;
    }

    @Override
    public int getViewTypeCount() {

        return viewTypeCount;
    }

    @Override
    public int getItemViewType(int position) {

        Integer type = viewTypeMap.get(getItem(position).getClass());

        return type == null ? super.getItemViewType(position) : type.intValue() ;
    }

    @Override
    public final Object getItem(int position) {
        return allDatas.get(position);
    }

    @Override
    public int getCount() {
        return allDatas.size();
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolderFactory viewHolderFactory = this.mFactory;

        AbstractViewHolder viewHolder = null;
        if (convertView != null){
            Object tag = convertView.getTag();
            if (tag != null && tag instanceof AbstractViewHolder){
                viewHolder = (AbstractViewHolder) tag;
                if (viewHolder.viewType != getItemViewType(position)){
                    viewHolder = null;
                }
            }
        }

        if (viewHolder == null){
            if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());

            viewHolder = viewHolderFactory.getViewHolder(position, mInflater);
            viewHolder.viewType = getItemViewType(position);
            viewHolder.viewRoot.setTag(viewHolder);
        }

        viewHolder.bindData(getItem(position));

        return viewHolder.viewRoot;
    }

    public interface ViewHolderFactory{

        AbstractViewHolder getViewHolder(int position,LayoutInflater inflater);
    }



    public static abstract class AbstractViewHolder<Data> implements IViewHolder<Data>{

        public static final int NO_TYPE = -1;

        protected final View viewRoot;

        private int viewType = NO_TYPE;

        protected AbstractViewHolder(View rootView){
            this.viewRoot = rootView;
        }

        protected AbstractViewHolder(int layoutId,LayoutInflater inflater){
            this(inflater.inflate(layoutId,null));
        }

        public View findViewById(int id){

           return viewRoot.findViewById(id);
        }

    }

}
