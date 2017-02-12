/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.luclx.rxandroid.testRx2;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CheeseActivity extends BaseSearchActivity {
    Observable<String> searchTextObservable;
    Observable<String> autoSearchObservable;
    Observable<String> mergeObservable;
    private Disposable disposable;

    @Override
    protected void onStart() {
        super.onStart();
        searchTextObservable = createClickButtonObservable();
        autoSearchObservable = createAutoSearchObservable();
        mergeObservable = Observable.merge(searchTextObservable, autoSearchObservable);

        disposable = mergeObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.io())
                .doOnNext(s -> {
                    showProgressBar();
                    Log.e("Operator thread", Thread.currentThread().getName());
                })
//                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .map(s -> {
                    Log.e("Operator thread", Thread.currentThread().getName());
                    return mCheeseSearchEngine.search(s);
                })
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((strings) -> {
                            Log.e("Operator thread", Thread.currentThread().getName());
                            hideProgressBar();
                            showResult(strings);
                        }
                );
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private Observable<String> createClickButtonObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                mSearchButton.setOnClickListener(view -> emitter.onNext(mQueryEditText.getText().toString()));
                emitter.setCancellable(() -> mSearchButton.setOnClickListener(null));
            }
        }).filter(s -> s.length() > 1);
    }

    private Observable<String> createAutoSearchObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                final TextWatcher textWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        emitter.onNext(editable.toString());
                    }
                };
                mQueryEditText.addTextChangedListener(textWatcher);
                emitter.setCancellable(() -> mQueryEditText.addTextChangedListener(null));
            }
        }).filter(s -> s.length() > 1).debounce(1000, TimeUnit.MILLISECONDS);
    }

//    private Observable<String> createTestObservable(){
//        return Observable.
//    }
}