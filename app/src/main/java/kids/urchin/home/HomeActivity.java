package kids.urchin.home;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import frame.activity.F_DEMO.ZDEMOActivity;
import frame.activity.F_DEMO.InjectView;
import kids.urchin.bean.Event;
import vm.view.ZDataTypeAdapter;

public class HomeActivity extends ZDEMOActivity {


    public static Intent beAsGuest(Context context){

        Intent intent = new Intent(context,HomeActivity.class);
        return intent;
    }


     @InjectView(viewId = R.id.event_insert)
     private View insertEvent;

     @InjectView(viewId = R.id.event_list)
     private ListView eventList;

     private ZDataTypeAdapter zAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);
    }

    @Override
    protected void onInjectDone(){

        insertEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ADoorActivity.startDoorActivity(HomeActivity.this);
            }
        });

        zAdapter = new ZDataTypeAdapter(new ZDataTypeAdapter.ViewHolderFactory() {

            @Override
            public ZDataTypeAdapter.AbstractViewHolder getViewHolder(int position, LayoutInflater inflater) {

                return new ViewHolder(inflater);
            }
        });

        eventList.setAdapter(zAdapter);

        zAdapter.append(new Event());
        zAdapter.append(new Event());
        zAdapter.append(new Event());
        zAdapter.append(new Event());
    }



    private static class ViewHolder extends ZDataTypeAdapter.AbstractViewHolder<Event>  {

        public ViewHolder(LayoutInflater inflater) {
            super(R.layout.home_item, inflater);
        }

        @Override
        public void bindData(Event event) {

        }


    }
}
