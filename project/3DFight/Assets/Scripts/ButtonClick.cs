using UnityEngine;
using System.Collections;

//按钮点击事件
public class ButtonClick : MonoBehaviour {

    public WidgetInventory widgetInventory;

    public UILabel repairLabel;
    public UILabel energyLabel;

    void Start()
    {
        widgetInventory = GameObject.FindWithTag("Player").GetComponent<WidgetInventory>();
        repairLabel = GameObject.FindGameObjectWithTag("RepairLabel").GetComponent<UILabel>();
        energyLabel = GameObject.FindGameObjectWithTag("EnergyLabel").GetComponent<UILabel>();
    }

    void Update()
    {
        repairLabel.text = widgetInventory.GetItemCount(WidgetInventory.InventoryItem.REPAIRKIT).ToString();
        energyLabel.text = widgetInventory.GetItemCount(WidgetInventory.InventoryItem.ENERGTPACK).ToString();
    }

    public void OnRepairButtonClick()
    {
        Debug.Log("生命按钮按下");
        widgetInventory.UseItem(WidgetInventory.InventoryItem.REPAIRKIT, 1);
    }

    public void OnEnergyButtonClick()
    {
        Debug.Log("能量按钮按下");
        widgetInventory.UseItem(WidgetInventory.InventoryItem.ENERGTPACK, 1);
    }


}
