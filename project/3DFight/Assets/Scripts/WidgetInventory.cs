using UnityEngine;
using System.Collections;
using System.Collections.Generic;

//处理捡起来的物品（数量、类型）
public class WidgetInventory : MonoBehaviour
{

    public enum InventoryItem
    {
        ENERGTPACK,  //用来恢复能量
        REPAIRKIT  //用来恢复生命值
    }

    public WidgetStatus playerStatus;

    private float repaorKitHealAmt = 3.0f;  //恢复3点生命值
    private float energyPackHealAmt = 5.0f;  //恢复5点能量值

    Dictionary<InventoryItem, int> widgetDict;

    void Start()
    {
        playerStatus = GetComponent<WidgetStatus>();
        widgetDict = new Dictionary<InventoryItem, int>();

        widgetDict.Add(InventoryItem.ENERGTPACK, 1);
        widgetDict.Add(InventoryItem.REPAIRKIT, 2);
    }

    //得到类型自增数量
    public void GetItem(InventoryItem item, int amoun)
    {
        widgetDict[item] += amoun;
        print(widgetDict[item]);
    }

    //使用能量包和生命值
    public void UseItem(InventoryItem item, int amoun)
    {
        if (widgetDict[item] <= 0)
        {
            return;
        }
        widgetDict[item] -= amoun;
        switch (item)
        {
            case InventoryItem.ENERGTPACK:
                playerStatus.AddEnergy(energyPackHealAmt);
                break;
            case InventoryItem.REPAIRKIT:
                playerStatus.AddHealth(repaorKitHealAmt);
                break;
        }
    }

    //现有的数量和总数对比
    public bool ComPareItemCount(InventoryItem comItem, int comNumber)
    {
        return widgetDict[comItem] >= comNumber;
    }

    //得到当前的数量值
    public int GetItemCount(InventoryItem comItem)
    {
        return widgetDict[comItem];
    }
}