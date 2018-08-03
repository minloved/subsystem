package vm.view;

import android.view.View;

public interface IViewHolder<DATA> {


    View findViewById(int id);

    void bindData(DATA data);

}
