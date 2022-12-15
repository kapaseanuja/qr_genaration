package com.example.generate_qr_code.multiLevelExpandableDemo
import com.example.generate_qr_code.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView.OnGroupExpandListener
import android.widget.TextView

class ThreeLevelListAdapter(
    private val context: Context,
    var parentHeaders: Array<String>,
    var secondLevel: List<Array<String>>,
    var data: List<LinkedHashMap<String, Array<String>>>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return parentHeaders.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {


        // no idea why this code is working
        return 1
    }

    override fun getGroup(groupPosition: Int): Any {
        return groupPosition
    }

    override fun getChild(group: Int, child: Int): Any {
        return child
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var view :View? = convertView
        if (convertView == null) {
            val layoutInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = layoutInflater.inflate(R.layout.row_first, null)
        }
        val text = view?.findViewById(R.id.rowParentText) as TextView
        text.text = parentHeaders[groupPosition]
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val secondLevelELV = SecondLevelExpandableListView(context)
        val headers = secondLevel[groupPosition]
        val childData: MutableList<Array<String>> = ArrayList()
        val secondLevelData: HashMap<String, Array<String>> = data[groupPosition]
        for (key in secondLevelData.keys) {
            childData.add(secondLevelData[key]!!)
        }
        secondLevelELV.setAdapter(SecondLevelAdapter(context, headers, childData))
        secondLevelELV.setGroupIndicator(null)

//        secondLevelELV.setOnGroupExpandListener(object : OnGroupExpandListener {
//            var previousGroup = -1
//            override fun onGroupExpand(groupPosition: Int) {
//                if (groupPosition != previousGroup) secondLevelELV.collapseGroup(previousGroup)
//                previousGroup = groupPosition
//            }
//        })
        return secondLevelELV
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}