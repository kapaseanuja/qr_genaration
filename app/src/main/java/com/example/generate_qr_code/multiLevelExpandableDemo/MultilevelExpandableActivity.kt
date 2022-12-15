package com.example.generate_qr_code.multiLevelExpandableDemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ExpandableListView.OnGroupExpandListener
import com.example.generate_qr_code.databinding.ActivityMultilevelExpandableBinding
import java.util.*


//class MultilevelExpandableActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMultilevelExpandableBinding
//    lateinit var listDataHeader:ArrayList<String>
//    lateinit var mItemHeaders: ArrayList<String>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_multilevel_expandable)
//        binding = ActivityMultilevelExpandableBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Init top level data
//        listDataHeader= ArrayList()
//
//        mItemHeaders= ArrayList()
//        mItemHeaders.add("Level1")
//        mItemHeaders.add("Level2")
//        mItemHeaders.add("Level3")
//
//        Collections.addAll(listDataHeader, mItemHeaders.toString())
//
//        if (binding.expandableListView != null) {
//            val parentLevelAdapter = ParentLevelAdapter(this, listDataHeader)
//            binding.expandableListView.setAdapter(parentLevelAdapter)
//
//            // display only one expand item
////            mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
////                int previousGroup = -1;
////                @Override
////                public void onGroupExpand(int groupPosition) {
////                    if (groupPosition != previousGroup)
////                        mExpandableListView.collapseGroup(previousGroup);
////                    previousGroup = groupPosition;
////                }
////            });
//        }
//    }
//}

class MultilevelExpandableActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMultilevelExpandableBinding
    var thirdLevelMovies: LinkedHashMap<String, Array<String>> = LinkedHashMap()
    var thirdLevelGames: LinkedHashMap<String, Array<String>> = LinkedHashMap()
    var thirdLevelWebSeries: LinkedHashMap<String, Array<String>> = LinkedHashMap()
    var secondLevel: MutableList<Array<String>> = ArrayList()


//      The Parent Group Names.
    var parent = arrayOf("Movies", "Games", "Web-Series") // comment this when uncomment bottom

//    The Movies Genre List.
    var movies = arrayOf("Horror", "Action", "Thriller/Drama")
//     The Games Genre List.
    var games = arrayOf("Fps", "Moba","Racing")
//     The Serials Genre List.
    var webSeries = arrayOf("Crime", "Family", "Comedy")


//   movies category has further genres
//    The Horror movie list.
    var horror = arrayOf("Conjuring", "Insidious", "The Ring")
//     The Action Movies List.
    var action = arrayOf("Jon Wick", "Die Hard", "Avengers")
//      The Thriller Movies List.
    var thriller = arrayOf("Imitation Game","Soldier", "Inception")


    // games category has further genres
    //    The Fps games.
    var fps = arrayOf("CS: GO", "Team Fortress 2", "Overwatch")
//     The Moba games.
    var moba = arrayOf("Dota 2", "League of Legends", "Smite")
//     The Racing games.
    var racing = arrayOf("NFS: Most Wanted", "Forza Motorsport 3", "EA: F1 2016")


    // serials genre list
    //    The Fps games.
    var crime = arrayOf("CSI: MIAMI", "X-Files", "True Detective", "Sherlock (BBC)")
    //     The Moba games.
    var family = arrayOf("Andy Griffith", "Full House", "The Fresh Prince of Bel-Air")
    //     The Racing games.
    var comedy = arrayOf("Family Guy", "Simpsons", "The Big Bang Theory")

    /**
     * The Data.
     */
    var data: MutableList<LinkedHashMap<String, Array<String>>> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.generate_qr_code.R.layout.activity_multilevel_expandable)
        binding = ActivityMultilevelExpandableBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // second level category names (genres)
        secondLevel.add(movies)
        secondLevel.add(games)
        secondLevel.add(webSeries)

        // movies category all data
        thirdLevelMovies[movies[0]] = horror
        thirdLevelMovies[movies[1]] = action
        thirdLevelMovies[movies[2]] = thriller

        // games category all data
        thirdLevelGames[games[0]] = fps
        thirdLevelGames[games[1]] = moba
        thirdLevelGames[games[2]] = racing

        // wrb series category all data
        thirdLevelWebSeries[webSeries[0]] = crime
        thirdLevelWebSeries[webSeries[1]] = family
        thirdLevelWebSeries[webSeries[2]] = comedy


        // all data
        data.add(thirdLevelMovies)
        data.add(thirdLevelGames)
        data.add(thirdLevelWebSeries);

        // parent adapter
        val threeLevelListAdapterAdapter = ThreeLevelListAdapter(this, parent, secondLevel, data)


        // set adapter
        binding.expandableListView.setAdapter(threeLevelListAdapterAdapter)


//        // OPTIONAL : Show one list at a time
//        binding.expandableListView!!.setOnGroupExpandListener(object : OnGroupExpandListener {
//            var previousGroup = -1
//            override fun onGroupExpand(groupPosition: Int) {
//                if (groupPosition != previousGroup)  binding.expandableListView!!.collapseGroup(previousGroup)
//                previousGroup = groupPosition
//            }
//        })
    }
}