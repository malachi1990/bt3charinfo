package model

import model.potara.Potara

class State(val potaraData : List<Potara>,
            val characters : List<Bt3Character>,
            val teams : List<MasterList>,
    ) {
}