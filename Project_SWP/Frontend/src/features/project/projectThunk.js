import customFetch from '../../utils/axios.js'
import { getJWTFromLocalStorage } from '../../utils/localStorage.js'

export const getAllProject = async (url, thunkAPI) => {
  try {
    const resp = await customFetch.get(url, {
      headers: {
        authorization: `Bearer ${getJWTFromLocalStorage()}`
      }
    })
    return resp.data
  } catch (error) {
    return thunkAPI.rejectWithValue(error.response.data.message)
  }
}

export const getProjectByIdThunk = async (url, thunkAPI) => {
  try {
    const resp = await customFetch.get(url, {
      headers: {
        authorization: `Bearer ${getJWTFromLocalStorage()}`
      }
    })
    return resp.data
  } catch (error) {
    return thunkAPI.rejectWithValue(error.response.data.message)
  }
}

export const deleteProjectByIdThunk = async (url, thunkAPI) => {
  try {
    const resp = await customFetch.delete(url, {
      headers: {
        authorization: `Bearer ${getJWTFromLocalStorage()}`
      }
    })
    return resp.data
  } catch (error) {
    return thunkAPI.rejectWithValue(error.response.data.message)
  }
}
export const addProjectThunk = async (url, project, thunkAPI) => {
  try {
    const resp = await customFetch.post(url, {
        creator:{
            id : project.id
        },
        classDto:{
            id: project.classDto.id
        },
        leader:{
            id: null
        },
        mentor:{
            id: project.mentor.id
        },
        isActive:true,
        projectName: project.projectName,
        groupName: project.groupName,
        title: project.title,
        description: project.description
    }
    , {
      headers: {
        authorization: `Bearer ${getJWTFromLocalStorage()}`
      }
    })
    return resp.data
  } catch (error) {
    return thunkAPI.rejectWithValue(error.response.data.message)
  }
}
export const updateProjectThunk = async (url, project, thunkAPI) => {
  console.log(project.members.map(mem => ({id: mem.id})))
  try {
    const resp = await customFetch.put(url, {
        classDto:{
            id: project.classDto.id
        },
        mentor:{
            id: project.mentor.id
        },
        leader:{
            id: project.leader.id
        },
        members: project.members.map(mem => ({id: mem.id})),
        isActive: project.isActive,
        projectName: project.projectName,
        groupName: project.groupName,
        title: project.title,
        description: project.description
    }
    , {
      headers: {
        authorization: `Bearer ${getJWTFromLocalStorage()}`
      }
    })
    return resp.data
  } catch (error) {
    return thunkAPI.rejectWithValue(error.response.data.message)
  }
}
