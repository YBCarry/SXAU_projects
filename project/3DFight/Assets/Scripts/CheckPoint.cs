using UnityEngine;
using System.Collections;

//复活点
public class CheckPoint : MonoBehaviour {

    public static CheckPoint isActivePT;  //当前的复活点
    public CheckPoint firstPT;
    public WidgetStatus playerStatus;

    void Start()
    {
        playerStatus = GameObject.FindWithTag("Player").GetComponent<WidgetStatus>();
        isActivePT = firstPT;
    }

    void OnTriggerEnter()
    {
        if (isActivePT != this)
        {
            isActivePT = this;
        }

        playerStatus.AddHealth(playerStatus.maxHpHealth);
        playerStatus.AddEnergy(playerStatus.maxBoost);
    }
    
}
