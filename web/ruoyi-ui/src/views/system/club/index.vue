<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="社团名称" prop="clubName">
        <el-input
          v-model="queryParams.clubName"
          placeholder="请输入社团名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="社团说明" prop="clubDesc">
        <el-input
          v-model="queryParams.clubDesc"
          placeholder="请输入社团说明"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="gmtCreate">
        <el-date-picker clearable
          v-model="queryParams.gmtCreate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>


<!--      <el-form-item label="是否删除" prop="isDelete">-->
<!--        <el-input-->
<!--          v-model="queryParams.isDelete"-->
<!--          placeholder="请输入是否删除"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="社团图标url" prop="clubIcon">-->
<!--        <el-input-->
<!--          v-model="queryParams.clubIcon"-->
<!--          placeholder="请输入社团图标url"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:club:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:club:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:club:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:club:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="clubList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="社团id" align="center" prop="id" />
      <el-table-column label="社团名称" align="center" prop="clubName" />
      <el-table-column label="社团说明" align="center" prop="clubDesc" />
<!--      <el-table-column label="社团详情" align="center" prop="clubDetail" />-->
      <el-table-column label="创建时间" align="center" prop="gmtCreate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtCreate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="state">
        <template slot-scope="scope">
          <span v-show="scope.row.state !== 1">待审核</span>
          <span v-show="scope.row.state === 1">审核通过</span>
        </template>
      </el-table-column>
      <el-table-column label="社团图标" align="center" prop="image" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.clubIcon" :width="50" :height="50"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="社团图标url" align="center" prop="clubIcon" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:club:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:club:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="pass(scope.row)"
          >审核通过</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改社团对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="社团名称" prop="clubName">
          <el-input v-model="form.clubName" placeholder="请输入社团名称" />
        </el-form-item>
        <el-form-item label="社团说明" prop="clubDesc">
          <el-input v-model="form.clubDesc" placeholder="请输入社团说明" />
        </el-form-item>
        <el-form-item label="指导老师" prop="theacher">
          <el-input v-model="form.theacher" placeholder="请输入指导老师" />
        </el-form-item>
        <el-form-item label="QQ号" prop="clubDesc">
          <el-input v-model="form.qqNUmber" placeholder="请输入QQ号" />
        </el-form-item>
        <el-form-item label="联系电话" prop="mobilePhone">
          <el-input v-model="form.mobilePhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="社团详情" prop="clubDetail">
          <el-input v-model="form.clubDetail" type="textarea" placeholder="请输入内容" />
        </el-form-item>
<!--        <el-form-item label="创建时间" prop="gmtCreate">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.gmtCreate"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择创建时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="是否删除" prop="isDelete">-->
<!--          <el-input v-model="form.isDelete" placeholder="请输入是否删除" />-->
<!--        </el-form-item>-->
        <el-form-item label="社团图标" prop="clubIcon">
          <image-upload v-model="form.clubIcon"/>
        </el-form-item>
<!--        <el-form-item label="社团图标url" prop="clubIcon">-->
<!--          <el-input v-model="form.clubIcon" placeholder="请输入社团图标url" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listClub, getClub, delClub, addClub, updateClub,pass } from "@/api/system/club";

export default {
  name: "Club",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 社团表格数据
      clubList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        clubName: null,
        clubDesc: null,
        clubDetail: null,
        gmtCreate: null,
        isDelete: null,
        clubIcon: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        clubName: [
          { required: true, message: "社团名称不能为空", trigger: "blur" }
        ],
        theacher: [
          { required: true, message: "指导老师不能为空", trigger: "blur" }
        ],
        mobilePhone: [
          { required: true, message: "电话不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询社团列表 */
    getList() {
      this.loading = true;
      listClub(this.queryParams).then(response => {
        this.clubList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        clubName: null,
        clubDesc: null,
        clubDetail: null,
        gmtCreate: null,
        isDelete: null,
        clubIcon: null,
        createTime: null,
        createBy: null,
        updateBy: null,
        updateTime: null,
        theacher: null,
        qqNumber: null,
        mobilePhone: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加社团";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getClub(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改社团";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateClub(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClub(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除社团编号为"' + ids + '"的数据项？').then(function() {
        return delClub(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },

    pass(row) {
      const id = row.id
      this.$modal.confirm('是否审核通过社团编号为"' + id + '"的数据项？').then(function() {
        return pass(id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("审核通过成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/club/export', {
        ...this.queryParams
      }, `club_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
