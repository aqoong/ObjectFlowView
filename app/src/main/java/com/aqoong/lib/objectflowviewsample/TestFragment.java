package com.aqoong.lib.objectflowviewsample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aqoong.lib.objectflowview.FlowObject;
import com.aqoong.lib.objectflowview.FlowObjectManager;
import com.aqoong.lib.objectflowview.ObjectFlowView;

/**
 * [ObjectFlowViewSample]
 * <p>
 * Class: TestFragment
 * <p>
 * Created by aqoong on 2019-12-24.
 * - Email  : cooldnjsdn@gmail.com
 * - GitHub : https://github.com/aqoong
 * <p>
 * Description:
 */
public class TestFragment extends Fragment {
    private ObjectFlowView vObjectFlow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vObjectFlow = view.findViewById(R.id.objectflow);
        vObjectFlow.setFlowObjectManager(setData());
    }

    private FlowObjectManager setData(){
        FlowObject[] objects = new FlowObject[10];
        for(int i = 0 ; i < 10 ; i++){
            FlowObject object = new FlowObject();
            object.setType(FlowObject.ObjectType.TYPE_TEXT);
            object.setStrText("TEST입니다. index : "+i);
            objects[i] = object;
        }

        FlowObjectManager manager = new FlowObjectManager(getContext(), objects);
        return manager;
    }
}
