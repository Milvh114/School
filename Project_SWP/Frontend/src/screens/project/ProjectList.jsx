import CardProject from '../../components/project/CardProject.jsx'
import HeaderContent from '../../components/common/layout/HeaderContent.jsx';
import { Link } from 'react-router-dom'
import React, {useEffect, useState, Suspense} from 'react'
import {useDispatch} from 'react-redux'
import {
  getListOfProject,
  getListOfProjectByClassWithoutPaging,
} from '../../features/project/projectSlice.js'
import {ToastContainer} from 'react-toastify'


const ProjectList = () => {
  const dispatch = useDispatch()
  const prePage = 'Dashboard'
  const [ projects, setProjects] = useState([])
  const [isLoading, setIsLoading] = useState(true);
  const [search, setSearch] = useState('')

  useEffect(() =>{
    const fetchData1 = async () => {
      await dispatch( getListOfProjectByClassWithoutPaging( {
        classDto: {
          id: 1
        }
      } ) ).then( ( response ) => {
        if(search.length==0){
          setProjects(response.payload)
        }else{
          setProjects(response.payload.filter(p => p.projectName.toLowerCase().startsWith(search.toLowerCase())))
        }
        // setProjects(response.payload)
      } )
    }
    fetchData1()
  }, [search] )

  function handleSearch (search) {
    setSearch(prev => prev+search)
}

  console.log(projects)

  return (
    <>
    <ToastContainer
        position='top-center'
        autoClose={ 1000 }
        style={ {width: '600px'} }
      />
    <div style={{ display: 'flex' }}>
      <HeaderContent
        pageTitle={'List of project'}
        pageName={'List of project'}
        prePage={prePage}
      />

      <div className="col-auto text-right float-right ml-auto">
        <div className="top-nav-search mr-5">
            <form>
              <input type="text" className="form-control" placeholder="Search here" onChange={(e)=>setSearch(e.target.value)}/>
              <button className="btn" type="">
                <i className="fas fa-search"></i>
              </button>
            </form>
          </div>
        <Link className={'btn btn-primary'} to={'/'}>
          Add <i className="fas fa-plus"></i>
        </Link>
      </div>
      </div>
      
      <section className="comp-section comp-cards">
        <div className="row">
          {
            projects.map(p => (

              // <Suspense key={p.id} fallback={<CardProject />}>
                <CardProject key={p.id} project={p} />
              // </Suspense>

            ))
          }
      
        
        </div>
      </section>

    </>
  )
}

export default ProjectList
