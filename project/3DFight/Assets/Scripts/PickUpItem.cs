using UnityEngine;
using System.Collections;

//掉落物品的处理
public class PickUpItem : MonoBehaviour {

    public WidgetInventory.InventoryItem itemType;
    public int itemAmout = 1;

    private bool pickup = false;


    void OnTriggerEnter(Collider coll)
    {
        if (pickup)
        {
            return;
        }
        //捡起物品
        WidgetInventory widgetInventory = coll.GetComponent<WidgetInventory>();
        widgetInventory.GetItem(itemType, itemAmout);

        pickup = true;
        Destroy(gameObject);
    }
}
