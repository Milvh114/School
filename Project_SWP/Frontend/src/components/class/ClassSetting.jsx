import React, { useState } from 'react'
import CardUpdateForm from './CardUpdateForm.jsx'
import CardSpecialSetting from './CardSpecialSetting.jsx'
import TabClass from './TabClass.jsx'

const tabSettings = ['update', 'special']
const ClassSetting = (prop) => {
  const tab = prop.tab
  const currentClass = prop.currentClass
  const [tabSetting, setTabSetting] = useState('update')
  return (
    <div className='card-body' style={{ display: tab === 'Setting' ? 'block' : 'none' }}>
      <div className='row'>
        <div className={'col-3'}>
          <ul className='nav nav-tabs nav-tabs-solid nav-justified'
              style={{
                display: 'flex',
                flexDirection: 'column',
                paddingRight: 0
              }}>
            {
              tabSettings.map(
                el => (
                  <TabClass el={el} tab={tabSetting} setTab={setTabSetting} key={el}/>
                )
              )
            }
          </ul>
        </div>
        <div className='col-1'></div>
        <CardUpdateForm
          currentClass={currentClass}
          tabSetting={tabSetting}
          setTabSetting={setTabSetting}/>
        <CardSpecialSetting
          currentClass={currentClass}
          tabSetting={tabSetting}
          setTabSetting={setTabSetting}/>
      </div>
    </div>
  )
}

export default ClassSetting
