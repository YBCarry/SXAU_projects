using UnityEngine;
using System.Collections;

public class WidgetHealth : MonoBehaviour {

    public UISlider widgetHealthSlider;
    public UISlider widgetEnergySlider;

    public WidgetStatus widgetStatus;

    void Start()
    {
        widgetHealthSlider = GameObject.Find("WidgetHealthBar").GetComponent<UISlider>();
        widgetEnergySlider = GameObject.Find("WidgetEnergyBar").GetComponent<UISlider>();

        widgetStatus = GameObject.FindWithTag("Player").GetComponent<WidgetStatus>();
    }

    void Update()
    {
        widgetHealthSlider.value = widgetStatus.hpHealth / widgetStatus.maxHpHealth;
        widgetEnergySlider.value = widgetStatus.boost / widgetStatus.maxBoost;
    }
}
