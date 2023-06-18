package org.d3if3049.mobpro1.parkirku.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if3049.mobpro1.parkirku.db.ParkirDao

class HistoriViewModel(db: ParkirDao) : ViewModel() {
    val data = db.getLastWarnet()

}