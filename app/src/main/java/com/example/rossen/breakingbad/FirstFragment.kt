package com.example.rossen.breakingbad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.rossen.breakingbad.web.ReposClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    //should be singleton and injected
    private val reposClient: ReposClient = ReposClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }


        val disposable = CompositeDisposable()
        disposable.add(reposClient.queryCharacters()
            //.delay(2000, TimeUnit.MILLISECONDS)  //just for testing
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ stargazers ->
                val a = stargazers
//                internalStargazersState.value =
//                    StargazersState.Stargazers(stargazers)
//                repository.stargazers = stargazers
//                disposable.dispose()
            }
                , { throwable ->
                    val b = throwable
//                    internalReposState.value =
//                        RepositoriesState.ReposError(throwable.message)
                    disposable.dispose()
                }
            ))
    }
}