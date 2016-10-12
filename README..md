# MagicProgressBar

[![](https://jitpack.io/v/iQuick/MagicProgressBar.svg)](https://jitpack.io/#iQuick/MagicProgressBar)


## Install

1. Step：
```
    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
```

2. Setp：
```
    dependencies {
            compile 'com.github.iQuick:MagicProgressBar:v1.0'
    }
```


## Use

```java
final MagicProgressBar pb = (MagicProgressBar) findViewById(R.id.pb);
final SeekBar sb = (SeekBar) findViewById(R.id.sb);

sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Log.d(getClass().getSimpleName(), "progress ==> " + i);
        pb.setProgress(i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
});
```