package com.example.generate_qr_code.multiLevelExpandableDemo

import com.example.generate_qr_code.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView


class SecondLevelAdapter(context: Context, headers: Array<String>, data: List<Array<String>>) :
        BaseExpandableListAdapter() {
        private val context: Context
        var data: List<Array<String>>
        var headers: Array<String>

        init {
            this.context = context
            this.data = data
            this.headers = headers
        }

        override fun getGroup(groupPosition: Int): Any {
            return headers[groupPosition]
        }

        override fun getGroupCount(): Int {
            return headers.size
        }

        override fun getGroupId(groupPosition: Int): Long {
            return groupPosition.toLong()
        }

        override fun getGroupView(
            groupPosition: Int,
            isExpanded: Boolean,
            convertView: View?,
            parent: ViewGroup?
        ): View {

            var view :View? = convertView
            if (convertView == null) {
                val layoutInflater = context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = layoutInflater.inflate(R.layout.row_second, null)
            }

            val text = view?.findViewById(R.id.rowSecondText) as TextView
            val groupText = getGroup(groupPosition).toString()
            text.text = groupText
            return view
        }

        override fun getChild(groupPosition: Int, childPosition: Int): Any {
            val childData: Array<String>
            childData = data[groupPosition]
            return childData[childPosition]
        }

        override fun getChildId(groupPosition: Int, childPosition: Int): Long {
            return childPosition.toLong()
        }

        override fun getChildView(
            groupPosition: Int,
            childPosition: Int,
            isLastChild: Boolean,
            convertView: View?,
            parent: ViewGroup?
        ): View {

            var view :View? = convertView
            if (convertView == null) {
                val layoutInflater = context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = layoutInflater.inflate(R.layout.row_third, null)
            }
            val textView = view?.findViewById(R.id.rowThirdText) as TextView
            val childArray = data[groupPosition]
            val text = childArray[childPosition]
            textView.text = text
            return view
        }

        override fun getChildrenCount(groupPosition: Int): Int {
            val children = data[groupPosition]
            return children.size
        }

        override fun hasStableIds(): Boolean {
            return true
        }

        override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
            return true
        }
    }